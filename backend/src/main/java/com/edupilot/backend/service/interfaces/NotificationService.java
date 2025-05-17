package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.NotificationDto;

public interface NotificationService {

    public void notifySync(NotificationDto notificationDto);

    public void notifyAsync(NotificationDto notificationDto);
}
