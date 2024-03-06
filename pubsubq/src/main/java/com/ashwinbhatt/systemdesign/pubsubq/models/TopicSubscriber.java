package com.ashwinbhatt.systemdesign.pubsubq.models;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {

    private AtomicInteger currentQPosition;
    private ISubscriber subscriber;

    public TopicSubscriber(ISubscriber subscriber) {
        this.currentQPosition = new AtomicInteger(0);
        this.subscriber = subscriber;
    }

    public void setCurrentQPosition(int newPosition) {
        currentQPosition = new AtomicInteger(newPosition);
    }

}
