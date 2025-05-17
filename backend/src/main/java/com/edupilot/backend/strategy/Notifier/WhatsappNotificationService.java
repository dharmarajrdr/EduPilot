package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.kafka.NotificationService.producer.NotificationProducer;
import com.edupilot.backend.model.enums.NotificationTopic;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WhatsappNotificationService implements NotificationService {

    private final NotificationProducer notificationProducer;
    private final Logger logger = LoggerFactory.getLogger(WhatsappNotificationService.class);

    /**
     * Notify over phone
     *
     * @param notificationDto
     */
    @Override
    public void notifySync(NotificationDto notificationDto) {

        logger.info("Sending notification to Whatsapp");
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
