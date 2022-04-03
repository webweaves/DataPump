package com.scottfree.datapump;

import java.util.Objects;

public class Topic {

    public static final String DEFAULT = "DEFAULT_TOPIC";

    private String topic;

    public Topic(String topic) {
        this.topic = topic;
    }

    public static Topic defaultTopic() {
        return new Topic(DEFAULT);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic1 = (Topic) o;
        return Objects.equals(topic, topic1.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }

    @Override
    public String toString() {
        return "Topic{topic='" + topic + "'}";
    }
}
