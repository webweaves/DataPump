package com.scottfree.datapump.subscribers;

import com.scottfree.datapump.Message;
import com.scottfree.datapump.DataStream;

/**
 * the default subscriber, use if no other subscribers are defined
 */
public class SubscriberDefault extends Subscriber {

    @Override
    public void receivedMessage(DataStream t, Message m) {
        System.out.println("DEFAULT: Im subscribed to " + t.getStream() + " so processing the data: " + m);
    }
}
