package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.Topic;
import com.scottfree.datapump.Message;

public class SubscriberToDatabase extends Subscriber {

    public SubscriberToDatabase(Topic ...topics) {
        super(topics);
    }

    @Override
    public void receivedMessage(Topic t, Message m) {
        System.out.println("DB: Im subscribed to " + t.getTopic() + " so processing the data: " + m);
    }
}
