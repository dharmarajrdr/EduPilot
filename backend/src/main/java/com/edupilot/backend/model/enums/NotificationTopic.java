package com.edupilot.backend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationTopic {

    EMAIL("email-notification-topic"),
    SMS("sms-notification-topic"),
    WHATSAPP("whatsapp-notification-topic");

    private final String topic;

}
