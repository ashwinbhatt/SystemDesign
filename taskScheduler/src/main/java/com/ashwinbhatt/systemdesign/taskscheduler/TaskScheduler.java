package com.ashwinbhatt.systemdesign.taskscheduler;

import com.ashwinbhatt.systemdesign.taskscheduler.exceptions.SchedulerManagerException;
import com.ashwinbhatt.systemdesign.taskscheduler.manager.SchedulerManager;
import com.ashwinbhatt.systemdesign.taskscheduler.manager.TaskManager;

public class TaskScheduler {

    private final SchedulerManager schedulerManager;

    public TaskScheduler(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
        new Thread(schedulerManager).start();
    }

    public void schedule(ITask task, int interval) throws SchedulerManagerException {
        TaskManager newTaskManger = new TaskManager(interval, task);
        schedulerManager.schedule(newTaskManger);
    }

}
