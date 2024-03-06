package com.ashwinbhatt.systemdesign.pubsubq;

import com.ashwinbhatt.systemdesign.pubsubq.common.Queue;
import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TestBaseClass {

    Queue queue = new Queue();
    // some default topics
    String tid1 = queue.createTopic("t1");
    String tid2 = queue.createTopic("t2");
    Topic t1 = queue.getTopic(tid1);
    Topic t2 = queue.getTopic(tid2);

    // default subscribers
    List<String> sub1Mess = new ArrayList<>();
    ISubscriber s1 = new TestSubscriber("1", sub1Mess, 5);


    List<String> sub2Mess = new ArrayList<>();
    ISubscriber s2 = new TestSubscriber("2", sub2Mess, 5);

    List<String> sub3Mess = new ArrayList<>();
    ISubscriber s3 = new TestSubscriber("3", sub3Mess, 5);
}
