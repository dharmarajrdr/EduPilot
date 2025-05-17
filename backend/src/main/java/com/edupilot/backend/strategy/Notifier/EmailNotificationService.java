package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.kafka.NotificationService.producer.NotificationProducer;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.NotificationTopic;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@AllArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final NotificationProducer notificationProducer;
    private final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    /**
     * Notify message via email
     *
     * @param notificationDto
     */
    @Override
    public void notifySync(NotificationDto notificationDto) {

        User fromUser = notificationDto.getFromUser();
        User toUser = notificationDto.getToUser();
        String subject = notificationDto.getSubject();
        String message = notificationDto.getMessage();

        logger.info("---------------------------------------------------------------");
        logger.info("from: {}", fromUser.getAccount().getEmail());
        logger.info("to: {}", toUser.getAccount().getEmail());
        logger.info("Subject: {}", subject);
        logger.info("Message: {}", message);
        logger.info("---------------------------------------------------------------");
    }

    /**
     * @param notificationDto
     */
    @Override
    public void notifyAsync(NotificationDto notificationDto) {

        notificationProducer.send(notificationDto, NotificationTopic.EMAIL);
    }
}
