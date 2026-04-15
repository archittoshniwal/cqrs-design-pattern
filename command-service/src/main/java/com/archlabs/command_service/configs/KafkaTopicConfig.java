package com.archlabs.command_service.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    @Bean
    public NewTopic userCreatedTopic() {
        return new NewTopic(KAFKA_USER_CREATED_TOPIC, 3, (short) 1);
    }
}