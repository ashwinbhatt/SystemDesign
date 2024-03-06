package com.ashwinbhatt.systemdesign.pubsubq.worker;

import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import com.ashwinbhatt.systemdesign.pubsubq.models.TopicSubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;
import lombok.Getter;

public class SubscriberWorker implements Runnable {

    @Getter
    private final TopicSubscriber topicSubscriber;
    private final Topic topic;

    public SubscriberWorker(TopicSubscriber topicSubscriber, Topic topic) {
        this.topicSubscriber = topicSubscriber;
        this.topic = topic;
    }

    public void run() {
        synchronized (topicSubscriber) {
            while(true) {
                int currPos = topicSubscriber.getCurrentQPosition().get();
                if(currPos >= topic.getTopicMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        System.out.println(String.format("Waiting interrupted for thread <%s>, with error!", "sub_"+topicSubscriber.getSubscriber().getSubscriberId(), e.getMessage()));
                        break;
                    }
                } else {
                    Message message = topic.getTopicMessages().get(currPos);
                    topicSubscriber.getSubscriber().consume(message);
                    topicSubscriber.getCurrentQPosition().compareAndSet(currPos, currPos+1);
                }
            }
        }
    }

    public void wakeUpSubscriberWorker() {
        synchronized (topicSubscriber) {
//            System.out.println(">>" + this.topicSubscriber.getSubscriber().getSubscriberId());
            topicSubscriber.notify();
        }
    }
}
