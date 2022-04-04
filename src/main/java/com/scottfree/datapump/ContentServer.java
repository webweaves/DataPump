package com.scottfree.datapump;

import com.scottfree.datapump.subscribers.Subscriber;
import com.scottfree.datapump.subscribers.SubscriberDefault;
import com.scottfree.datapump.subscribers.SubscriberToDatabase;
import com.scottfree.datapump.subscribers.SubscriberToDisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContentServer {

    private static ContentServer serverInstance;
    private Map<DataStream, List<Subscriber>> subscriberLists;

    private Logger logger = LoggerFactory.getLogger(ContentServer.class);

    @PostConstruct
    public void init() {
        /**
         * register a default subscriber,
         * in this case: write to disk subscriber
         */
        ContentServer.getInstance().registerSubscriber(
            new SubscriberToDisk(),
            DataStream.defaultStream());

        ContentServer.getInstance().registerSubscriber(
            new SubscriberToDatabase(),
            //define data streams to listen to
            DataStream.defaultStream(),
            new DataStream("SNOOKER"),
            new DataStream("BOXING"));
    }

    private ContentServer() {
        this.subscriberLists = new HashMap<>();
    }

    public static ContentServer getInstance() {
        if (serverInstance == null) {
            serverInstance = new ContentServer();
        }
        return serverInstance;
    }

    /**
     * send a message to all subscribers of data stream
     * @param stream
     * @param m
     */
    public void sendMessage(DataStream stream, Message m) {
        List<Subscriber> subs = subscriberLists.get(stream);

        /**
         * if nobody is subscribed then no need to publish the information, it will be lost
         */
        if (subs == null) {
            subs = new ArrayList<>();
            subs.add(new SubscriberDefault());
        }

        for (Subscriber s : subs) {
            s.receivedMessage(stream, m);
        }
    }

    /**
     * register a subscriber to a data stream
     *
     * @param subscriber
     */
    public void registerSubscriber(Subscriber subscriber, DataStream... streams) {
        for (DataStream s: streams) {
            logger.debug("Registering {} to the data stream {}", subscriber, s);
            System.out.println("Registering "+subscriber+" to the stream "+s);

            subscriberLists.computeIfAbsent(s, k -> new ArrayList<>()).add(subscriber);
        }
    }
}
