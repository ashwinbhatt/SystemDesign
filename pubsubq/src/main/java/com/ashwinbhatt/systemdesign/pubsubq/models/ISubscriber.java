package com.ashwinbhatt.systemdesign.pubsubq.models;

public interface ISubscriber {

    public String getSubscriberId();
    public void consume(Message message);

}
