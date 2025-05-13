package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto {

    private User fromUser;

    private User toUser;

    private String subject;

    private String body;
}
