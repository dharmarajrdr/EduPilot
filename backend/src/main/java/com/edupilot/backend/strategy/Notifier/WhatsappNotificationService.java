package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.kafka.NotificationService.producer.NotificationProducer;
import com.edupilot.backend.model.enums.NotificationTopic;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WhatsappNotificationService implements NotificationService {

    private final NotificationProducer notificationProducer;

    /**
     * Notify over phone
     *
     * @param notificationDto
     */
    @Override
    public void notifySync(NotificationDto notificationDto) {

    }

    /**
     * Notify over phone
     *
     * @param notificationDto
     */
    @Override
    public void notifyAsync(NotificationDto notificationDto) {

        notificationProducer.send(notificationDto, NotificationTopic.WHATSAPP);
    }
}
