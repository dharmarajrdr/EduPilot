package com.edupilot.backend.kafka.NotificationService.consumer;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WhatsappNotificationConsumer {

    private final NotificationService notificationService;

    public WhatsappNotificationConsumer(@Qualifier("whatsappNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "whatsapp-notification-topic", groupId = "whatsapp-group")
    public void consume(NotificationDto dto) {

        notificationService.notifySync(dto);
    }
}
