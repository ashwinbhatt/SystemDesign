package com.ashwinbhatt.systemdesign.taskscheduler.samples;

import com.ashwinbhatt.systemdesign.taskscheduler.ITask;
import com.ashwinbhatt.systemdesign.taskscheduler.TaskScheduler;
import com.ashwinbhatt.systemdesign.taskscheduler.exceptions.SchedulerManagerException;
import com.ashwinbhatt.systemdesign.taskscheduler.manager.SchedulerManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
public class Main {

    public static void main(String[] args) throws SchedulerManagerException, InterruptedException {

        SchedulerManager schedulerManager = new SchedulerManager(10, new ArrayBlockingQueue<>(10));
        TaskScheduler taskScheduler = new TaskScheduler(schedulerManager);

//        Thread.sleep(2000);
        ITask p1 = new NumberPrinterTask(1);
        ITask p2 = new NumberPrinterTask(2);
        ITask p3 = new NumberPrinterTask(3);
        ITask p4 = new NumberPrinterTask(4);
        taskScheduler.schedule(p1, 3);
        taskScheduler.schedule(p2, 4);
//        taskScheduler.schedule(p3, 3);
        Thread.sleep(1000);
        log.info("MAIN THREAD ENDS");
    }
}
