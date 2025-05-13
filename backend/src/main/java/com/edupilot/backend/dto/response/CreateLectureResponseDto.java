package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLectureResponseDto {

    private String title;

    private String description;

    private UUID videoId;

    private Long courseId;

    public static CreateLectureResponseDto fromLecture(Lecture lecture) {

        return CreateLectureResponseDto.builder()
                .title(lecture.getTitle())
                .description(lecture.getDescription())
                .videoId(lecture.getVideoId())
                .courseId(lecture.getCourse().getId())
                .build();
    }
}
