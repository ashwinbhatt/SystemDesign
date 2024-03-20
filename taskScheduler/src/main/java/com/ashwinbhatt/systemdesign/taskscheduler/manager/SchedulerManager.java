package com.ashwinbhatt.systemdesign.taskscheduler.manager;

import com.ashwinbhatt.systemdesign.taskscheduler.exceptions.SchedulerManagerException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SchedulerManager implements Runnable {

    private final int EXECUTION_QUEUE_WAIT_SEC;
    private final BlockingQueue<TaskManager> executionQueue;
    private TaskManager currentlyExecutingTask;


    public SchedulerManager(int executionQueueWaitSec, BlockingQueue<TaskManager> executionQueue) {
        EXECUTION_QUEUE_WAIT_SEC = executionQueueWaitSec;
        this.executionQueue = executionQueue;
    }

    public void schedule(TaskManager taskManager) throws SchedulerManagerException {
        boolean res = executionQueue.offer(taskManager);
        if(!res) {
            throw new SchedulerManagerException("Failed to add to task queue");
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        long overTime = System.currentTimeMillis();
        int prevTaskWaitPeriodSec = EXECUTION_QUEUE_WAIT_SEC;
        boolean isFirst = true;
        while(true) {
            if(currentlyExecutingTask != null) {
                synchronized (currentlyExecutingTask) {
                    currentlyExecutingTask.wait();
                }
                overTime = System.currentTimeMillis();
                prevTaskWaitPeriodSec = currentlyExecutingTask.getInterval();
                log.info("Scheduler Manager: Task over! Wait Period is " + prevTaskWaitPeriodSec + " secs");
            }
            long qWaitPeriod = Math.min(EXECUTION_QUEUE_WAIT_SEC, prevTaskWaitPeriodSec);
            TaskManager newTask = executionQueue.poll(qWaitPeriod, TimeUnit.SECONDS);
            if(newTask == null) {
                log.info("Scheduler Manager Thread: Nothing to process!");
                currentlyExecutingTask = null;
                continue;
            }
            long previousTaskCompletionDuration = System.currentTimeMillis() - overTime;
            if(!isFirst && previousTaskCompletionDuration < prevTaskWaitPeriodSec * 1000L) {
                long sleepTime = prevTaskWaitPeriodSec * 1000L - previousTaskCompletionDuration;
                log.info("Sleeping for calculated time: "+sleepTime);
                Thread.sleep(sleepTime);
            }
            isFirst = false;
            currentlyExecutingTask = newTask;
            new Thread(currentlyExecutingTask).start();
            log.info("Scheduler Manager Thread: Started new thread!");
        }
    }

}
