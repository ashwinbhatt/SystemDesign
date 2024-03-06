package com.ashwinbhatt.systemdesign.pubsubq;

import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import org.junit.Assert;
import org.junit.Test;

public class PositionChangeTest extends TestBaseClass{

    @Test
    public void test1() throws InterruptedException {
        queue.subscribe(t1, s1);
        queue.subscribe(t2, s2);

        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));
        queue.publish(t1, new Message("1"));
        queue.publish(t2, new Message("2"));

        int waitPeriod = 10 * 1000;
        Thread.sleep(waitPeriod);
        queue.changeSubscriberPosition(t1, s1, 0);

        Thread.sleep(waitPeriod);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <1>, Expected: <%s>, actual: <%s>", 8, sub1Mess.size()), sub1Mess.size() == 8);
        Assert.assertTrue(String.format("Mismatch messages for subscriber <2>, Expected: <%s>, actual: <%s>", 4, sub2Mess.size()), sub2Mess.size() == 4);

        for(String mess: sub1Mess) {
            Assert.assertEquals(mess, "1");
        }

        for(String mess: sub2Mess) {
            Assert.assertEquals(mess, "2");
        }
    }

}
