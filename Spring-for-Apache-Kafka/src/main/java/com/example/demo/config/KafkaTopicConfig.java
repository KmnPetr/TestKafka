package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder
                .name("javaguides")
                .partitions(10)//можно не указывать
                .build();
    }
    @Bean
    public NewTopic javaguidesJsonTopic(){
        return TopicBuilder
                .name("javaguides_json")
                .partitions(10)//можно не указывать
                .build();
    }
}
