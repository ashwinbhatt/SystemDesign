package com.ashwinbhatt.systemdesign.pubsubq.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {

    private final String topicName;
    private final String topicId;
    private final List<Message> topicMessages;
    private final List<TopicSubscriber> topicSubscribers;

    public Topic(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.topicMessages = new ArrayList();
        this.topicSubscribers = new ArrayList();
    }
}
