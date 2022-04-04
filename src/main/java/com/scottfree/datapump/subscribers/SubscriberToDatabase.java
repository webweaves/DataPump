package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.DataStream;
import com.scottfree.datapump.Message;

public class SubscriberToDatabase extends Subscriber {

    public SubscriberToDatabase(DataStream... streams) {
        super(streams);
    }

    @Override
    public void receivedMessage(DataStream s, Message m) {
        System.out.println("DB: Im subscribed to " + s.getStream() + " so processing the data: " + m);
    }
}
