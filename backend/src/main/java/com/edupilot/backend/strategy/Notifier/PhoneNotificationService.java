package com.edupilot.backend.strategy.Notifier;

import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneNotificationService implements NotificationService {

    /**
     * Notify over phone
     *
     * @param notificationDto
     */
    @Override
    public void notify(NotificationDto notificationDto) {

    }
}
