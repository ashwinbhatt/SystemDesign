package com.ashwinbhatt.systemdesign.pubsubq.worker;

import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;
import com.ashwinbhatt.systemdesign.pubsubq.models.TopicSubscriber;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicManager {

    @Getter
    private final Topic topic;
    @Getter
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public TopicManager(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish(Message message) {
        topic.getTopicMessages().add(message);
        synchronized (topic) {
            updateSubscriberAndWake();
        }
    }

    public void updateSubscriberAndWake() {
        for(TopicSubscriber topicSubscriber: topic.getTopicSubscribers()) {
            updateSpecificSubscriberAndAwake(topicSubscriber);
        }
    }

    public void updateSpecificSubscriberAndAwake(TopicSubscriber topicSubscriber) {
        String subId = topicSubscriber.getSubscriber().getSubscriberId();
        if(!subscriberWorkers.containsKey(subId)) {
            SubscriberWorker subscriberWorker = new SubscriberWorker(topicSubscriber, topic);
            subscriberWorkers.put(subId, subscriberWorker);
            Thread newSubWorkerThread = new Thread(subscriberWorker);
            newSubWorkerThread.start();
        }
        subscriberWorkers.get(subId).wakeUpSubscriberWorker();
    }

}
