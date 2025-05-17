package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.model.User;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@AllArgsConstructor
public class EmailNotificationService implements NotificationService {

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
        System.out.println("=========================================================");
        System.out.println("from: " + fromUser.getAccount().getEmail());
        System.out.println("to: " + toUser.getAccount().getEmail());
        System.out.println("Subject: " + subject);
        System.out.println(message);
        System.out.println("=========================================================");
    }

    /**
     * @param notificationDto
     */
    @Override
    public void notifyAsync(NotificationDto notificationDto) {

    }
}
