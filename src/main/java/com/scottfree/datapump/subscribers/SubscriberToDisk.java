package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.Message;
import com.scottfree.datapump.DataStream;

public class SubscriberToDisk extends Subscriber {

    @Override
    public void receivedMessage(DataStream t, Message m) {
        System.out.println("DISK: Im subscribed to " + t.getStream() + " so processing the data");
    }
}
