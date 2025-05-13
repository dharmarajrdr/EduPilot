package com.edupilot.backend.dto.request;

import lombok.Data;

@Data
public class DeleteLectureRequestDto {

    private Long courseId;

    private Long lectureId;
}
