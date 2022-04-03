package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.Message;
import com.scottfree.datapump.Topic;

public class SubscriberToDisk extends Subscriber {

    @Override
    public void receivedMessage(Topic t, Message m) {
        System.out.println("DISK: Im subscribed to " + t.getTopic() + " so processing the data");
    }
}
