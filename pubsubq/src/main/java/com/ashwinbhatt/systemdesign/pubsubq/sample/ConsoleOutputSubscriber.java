package com.ashwinbhatt.systemdesign.pubsubq.sample;

import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Message;

public class ConsoleOutputSubscriber implements ISubscriber {

    private final String id;

    public ConsoleOutputSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getSubscriberId() {
        return id;
    }

    @Override
    public void consume(Message message) {
        System.out.println("Sub "+ id + " consumer start message: " + message.getMessage());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sub "+ id + " consumer end message: " + message.getMessage());
    }
}
