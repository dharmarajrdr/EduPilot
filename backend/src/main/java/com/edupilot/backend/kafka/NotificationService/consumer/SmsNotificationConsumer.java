package com.edupilot.backend.kafka.NotificationService.consumer;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationConsumer {

    private final NotificationService notificationService;

    public SmsNotificationConsumer(@Qualifier("smsNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "sms-notification-topic", groupId = "sms-group")
    public void consume(NotificationDto dto) {

        notificationService.notifySync(dto);
    }
}

