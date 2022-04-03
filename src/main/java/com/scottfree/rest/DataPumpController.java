package com.scottfree.rest;

import com.scottfree.datapump.ContentServer;
import com.scottfree.datapump.Message;
import com.scottfree.datapump.Topic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DataPumpController {

    @GetMapping("/ingest")
    public void processData(@RequestParam(value = "topic", required = false) String topic,
                            @RequestParam(value = "data", required = false) String data) {

        getContentServer().sendMessage(
            //if no topic specified then send to the default topic
            new Topic(Optional.ofNullable(topic).orElse(Topic.DEFAULT)),
            new Message(data));
    }

    public ContentServer getContentServer() {
        return ContentServer.getInstance();
    }
}
