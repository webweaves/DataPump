package com.scottfree.datapump;

import com.scottfree.datapump.subscribers.Subscriber;
import com.scottfree.datapump.subscribers.SubscriberToDatabase;
import com.scottfree.datapump.subscribers.SubscriberToDisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Component
public class ContentServer {

    private static ContentServer serverInstance;
    private Hashtable<Topic, List<Subscriber>> subscriberLists;

    private Logger logger = LoggerFactory.getLogger(ContentServer.class);

    @PostConstruct
    public void init() {
        /**
         * register a default subscriber,
         * in this case: write to disk subscriber
         */
        ContentServer.getInstance().registerSubscriber(
            new SubscriberToDisk(),
            Topic.defaultTopic());

        ContentServer.getInstance().registerSubscriber(
                new SubscriberToDatabase(),
                Topic.defaultTopic(),
                new Topic("SNOOKER"),
                new Topic("BOXING"));
    }

    private ContentServer() {
        this.subscriberLists = new Hashtable<>();
    }

    public static ContentServer getInstance() {
        if (serverInstance == null) {
            serverInstance = new ContentServer();
        }
        return serverInstance;
    }

    /**
     * send a message to all subscribers of topic
     * @param topic
     * @param m
     */
    public void sendMessage(Topic topic, Message m) {
        List<Subscriber> subs = subscriberLists.get(topic);

        /**
         * if nobody is subscribed then no need to publish the information, it will be lost
         */
        if (subs == null) {
            return;
        }

        for (Subscriber s : subs) {
            s.receivedMessage(topic, m);
        }
    }

    /**
     * register a subscriber to a topic
     *
     * @param subscriber
     */
    public void registerSubscriber(Subscriber subscriber, Topic... topics) {
        for (Topic t: topics) {
            logger.debug("Registering {} to the topic {}", subscriber, t);
            System.out.println("Registering "+subscriber+" to the topic "+t);

            subscriberLists.computeIfAbsent(t, k -> new ArrayList<>()).add(subscriber);
        }
    }
}
