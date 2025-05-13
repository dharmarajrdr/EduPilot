package com.edupilot.backend.dto.request;

import lombok.Data;

@Data
public class CreateCommentRequestDto {

    private Long courseId;

    private Long lectureId;

    private String message;
}
