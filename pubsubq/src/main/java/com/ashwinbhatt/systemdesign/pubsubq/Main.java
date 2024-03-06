package com.ashwinbhatt.systemdesign.pubsubq;

import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;
import com.ashwinbhatt.systemdesign.pubsubq.common.Queue;
import com.ashwinbhatt.systemdesign.pubsubq.sample.ConsoleOutputSubscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();

        String tid1 = queue.createTopic("t1");
        String tid2 = queue.createTopic("t2");

        Topic t1 = queue.getTopic(tid1);
        Topic t2 = queue.getTopic(tid2);

        ISubscriber s1 = new ConsoleOutputSubscriber("1");
        ISubscriber s2 = new ConsoleOutputSubscriber("2");
        ISubscriber s3 = new ConsoleOutputSubscriber("3");

        queue.subscribe(t1, s1);
        queue.subscribe(t2, s2);
        queue.subscribe(t1, s3);

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t1, new Message("3"));
        queue.publish(t2, new Message("4"));
        queue.publish(t1, new Message("5"));
        queue.publish(t2, new Message("6"));

        // change position of subscriber 3 after 10 seconds
        Thread.sleep(10*1000);
        queue.changeSubscriberPosition(t1, s3, 0);
    }
}
