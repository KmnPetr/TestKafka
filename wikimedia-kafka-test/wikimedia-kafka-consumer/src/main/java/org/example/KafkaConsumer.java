package org.example;

import org.example.models.WikimediaData;
import org.example.repositories.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private WikimediaDataRepository repository;
    @Autowired
    public KafkaConsumer(WikimediaDataRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "wikimedia_recentchange",groupId = "my_group")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event message receiver -> %s",eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        repository.save(wikimediaData);
    }
}
