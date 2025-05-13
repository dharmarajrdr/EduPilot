package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailNotificationService implements NotificationService {

    /**
     * Notify message via email
     *
     * @param notificationDto
     */
    @Override
    public void notify(NotificationDto notificationDto) {

    }
}
