package com.wolroys.ensservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic mailTopic() {
        return TopicBuilder.name("mail-topic").partitions(1).build();
    }


}
