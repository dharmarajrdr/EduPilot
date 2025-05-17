package com.edupilot.backend.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class NotificationConfig {

    @Bean
    public NewTopic emailNotificationTopic() {
        return TopicBuilder.name("email-notification-topic").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic smsNotificationTopic() {
        return TopicBuilder.name("sms-notification-topic").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic whatsappNotificationTopic() {
        return TopicBuilder.name("whatsapp-notification-topic").partitions(1).replicas(1).build();
    }
}
