package org.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChengesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChengesProducer.class);
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public WikimediaChengesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * жизнь прекрасна
     */
    public void sendMessage() throws InterruptedException {
        //to read real time stream data from wikimedia, we use event sourse
        EventHandler eventHandler = new WikimediaChengesHandler(kafkaTemplate,"wikimedia_recentchange");
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create("https://stream.wikimedia.org/v2/stream/recentchange"));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
