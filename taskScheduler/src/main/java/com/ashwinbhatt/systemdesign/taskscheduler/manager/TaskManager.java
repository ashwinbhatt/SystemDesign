package com.ashwinbhatt.systemdesign.taskscheduler.manager;

import com.ashwinbhatt.systemdesign.taskscheduler.ITask;
import lombok.Getter;

@Getter
public class TaskManager implements Runnable {

    private final int interval;
    private final ITask iTask;

    public TaskManager(int interval, ITask iTask) {
        this.interval = interval;
        this.iTask = iTask;
    }

    @Override
    public void run() {
        iTask.execute();
        synchronized (this) {
            notify();
        }
    }
}
