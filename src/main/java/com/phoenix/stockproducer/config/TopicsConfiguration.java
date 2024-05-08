package com.phoenix.stockproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicsConfiguration {

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Value("${topic.partitions}")
    public Integer partition;

    @Value("${topic.replicas}")
    public Integer replicas;

    @Bean
    public NewTopic inventoryEvents() {
        return TopicBuilder.name(topicName)
                .partitions(partition)
                .replicas(replicas)
                .build();
    }
}
