package com.ashwinbhatt.systemdesign.pubsubq.common;

import com.ashwinbhatt.systemdesign.pubsubq.models.ISubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.models.Message;
import com.ashwinbhatt.systemdesign.pubsubq.models.Topic;
import com.ashwinbhatt.systemdesign.pubsubq.models.TopicSubscriber;
import com.ashwinbhatt.systemdesign.pubsubq.worker.SubscriberWorker;
import com.ashwinbhatt.systemdesign.pubsubq.worker.TopicManager;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {

    private final Map<String, TopicManager> topicManagerMap;
    private final Map<String, SubscriberWorker> subscriberWorkerMap;

    public Queue() {
        this.topicManagerMap = new HashMap<>();
        this.subscriberWorkerMap = new HashMap<>();
    }

    public String createTopic(String topicName) {
        String topicId = UUID.randomUUID().toString();
        Topic topic = new Topic(topicName, topicId);
        TopicManager topicManager = new TopicManager(topic);
        topicManagerMap.put(topicId, topicManager);
        return topicId;
    }

    public void publish(Topic topic, Message message) {
        @NonNull TopicManager topicManager = topicManagerMap.get(topic.getTopicId());
        new Thread(() -> topicManager.publish(message)).start();
    }

    public void subscribe(Topic topic, ISubscriber subscriber) {
        TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber);
        topic.getTopicSubscribers().add(topicSubscriber);
    }

    public Topic getTopic(String id) {
        return topicManagerMap.get(id).getTopic();
    }

    public void changeSubscriberPosition(Topic topic, ISubscriber subscriber, int newPosition) {
        TopicManager topicManager = topicManagerMap.get(topic.getTopicId());
        TopicSubscriber topicSubscriber = topicManager.getSubscriberWorkers().get(subscriber.getSubscriberId()).getTopicSubscriber();
        topicSubscriber.setCurrentQPosition(newPosition);
        new Thread(() -> topicManager.updateSpecificSubscriberAndAwake(topicSubscriber)).start();
    }
}
