package com.edupilot.backend.kafka.NotificationService.producer;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.model.enums.NotificationTopic;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public void send(NotificationDto notificationDto, NotificationTopic notificationTopic) {

        String topicName = notificationTopic.getTopic();

        Message<NotificationDto> message = MessageBuilder.withPayload(notificationDto).setHeader(KafkaHeaders.TOPIC, topicName).build();
        kafkaTemplate.send(message);
    }
}
