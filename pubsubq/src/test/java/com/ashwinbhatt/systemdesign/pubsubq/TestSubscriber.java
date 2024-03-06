package com.ashwinbhatt.systemdesign.pubsubq;

import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Message;

import java.util.List;

public class TestSubscriber implements ISubscriber {

    List<String> messages;
    String id;

    int waitPeriodSec;

    public TestSubscriber(String id, List<String> messages, int waitPeriodSec) {
        this.messages = messages;
        this.id = id;
        this.waitPeriodSec = waitPeriodSec;
    }

    @Override
    public String getSubscriberId() {
        return id;
    }

    @Override
    public void consume(Message message) {
        messages.add(message.getMessage());
    }

}
