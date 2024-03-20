package com.ashwinbhatt.systemdesign.taskscheduler.samples;

import com.ashwinbhatt.systemdesign.taskscheduler.ITask;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class NumberPrinterTask implements ITask {

    private final long SLEEP_INTERVAL;

    public NumberPrinterTask(long sleepInterval) {
        SLEEP_INTERVAL = sleepInterval;
    }


    @Override
    public void execute() {
        for(int i=0;i<3;i++) {
            try {
                Thread.sleep(SLEEP_INTERVAL * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Iteration " + i+ " printed!");
        }
        log.info("Task over!!");
    }
}
