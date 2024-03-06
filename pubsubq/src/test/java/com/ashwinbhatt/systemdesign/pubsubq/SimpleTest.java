package com.ashwinbhatt.systemdesign.pubsubq;

import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleTest extends TestBaseClass {

    String tid3 = queue.createTopic("t3");
    Topic t3 = queue.getTopic(tid3);

    String tid4 = queue.createTopic("t4");
    Topic t4 = queue.getTopic(tid4);

    String tid5 = queue.createTopic("t5");
    Topic t5 = queue.getTopic(tid5);


    List<String> sub4Mess = new ArrayList<>();
    ISubscriber s4 = new TestSubscriber("4", sub4Mess , 4);


    List<String> sub5Mess = new ArrayList<>();
    ISubscriber s5 = new TestSubscriber("5", sub5Mess, 5);


    @Test
    public void test1() throws InterruptedException {
        queue.subscribe(t1, s1);
        queue.subscribe(t2, s2);
        queue.subscribe(t3, s3);
        queue.subscribe(t4, s4);
        queue.subscribe(t5, s5);

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t3, new Message("3"));
        queue.publish(t4, new Message("4"));
        queue.publish(t5, new Message("5"));

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t3, new Message("3"));
        queue.publish(t4, new Message("4"));
        queue.publish(t5, new Message("5"));

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t3, new Message("3"));
        queue.publish(t4, new Message("4"));
        queue.publish(t5, new Message("5"));

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t3, new Message("3"));
        queue.publish(t4, new Message("4"));
        queue.publish(t5, new Message("5"));

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t3, new Message("3"));
        queue.publish(t4, new Message("4"));
        queue.publish(t5, new Message("5"));

        int testCompletionWaitPeriod = 30 * 1000;

        Thread.sleep(testCompletionWaitPeriod);


        Assert.assertTrue(String.format("Mismatch messages for subscriber <1>, Expected: <%s>, actual: <%s>", 5, sub1Mess.size()), sub1Mess.size() == 5);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <2>, Expected: <%s>, actual: <%s>", 5, sub2Mess.size()), sub2Mess.size() == 5);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <3>, Expected: <%s>, actual: <%s>", 5, sub3Mess.size()), sub3Mess.size() == 5);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <3>, Expected: <%s>, actual: <%s>", 5, sub4Mess.size()), sub4Mess.size() == 5);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <4>, Expected: <%s>, actual: <%s>", 5, sub5Mess.size()), sub5Mess.size() == 5);

        for(String mess: sub1Mess) {
            Assert.assertEquals(mess, "1");
        }

        for(String mess: sub2Mess) {
            Assert.assertEquals(mess, "2");
        }

        for(String mess: sub3Mess) {
            Assert.assertEquals(mess, "3");
        }

        for(String mess: sub4Mess) {
            Assert.assertEquals(mess, "4");
        }

        for(String mess: sub5Mess) {
            Assert.assertEquals(mess, "5");
        }
    }

}
