package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.ContentServer;
import com.scottfree.datapump.Message;
import com.scottfree.datapump.Topic;

public abstract class Subscriber {

    public Subscriber(Topic...topics) {
        for (Topic t : topics) {
            ContentServer.getInstance().registerSubscriber(this, t);
        }
    }

    public abstract void receivedMessage(Topic t, Message m);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}