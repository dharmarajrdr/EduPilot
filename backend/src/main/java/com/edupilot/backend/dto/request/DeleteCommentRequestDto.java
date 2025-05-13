package com.edupilot.backend.dto.request;

import lombok.Data;

@Data
public class DeleteCommentRequestDto {

    private Long courseId;

    private Long lectureId;
}
