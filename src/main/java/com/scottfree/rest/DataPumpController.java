package com.scottfree.rest;

import com.scottfree.datapump.ContentServer;
import com.scottfree.datapump.Message;
import com.scottfree.datapump.DataStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DataPumpController {

    @GetMapping("/ingest")
    public void processData(@RequestParam(value = "stream", required = false) String stream,
                            @RequestParam(value = "data", required = false) String data) {

        getContentServer().sendMessage(
            //if no stream specified then send to the default stream
            new DataStream(Optional.ofNullable(stream).orElse(DataStream.DEFAULT)),
            new Message(data));
    }

    public ContentServer getContentServer() {
        return ContentServer.getInstance();
    }
}