package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.ContentServer;
import com.scottfree.datapump.Message;
import com.scottfree.datapump.DataStream;

public abstract class Subscriber {

    public Subscriber(DataStream...streams) {
        for (DataStream s : streams) {
            ContentServer.getInstance().registerSubscriber(this, s);
        }
    }

    public abstract void receivedMessage(DataStream s, Message m);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}