package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Lecture;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateLectureRequestDto {

    private String title;

    private String description;

    private UUID videoId;

    private Long courseId;

    public Lecture toLecture() {

        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setDescription(description);
        lecture.setVideoId(videoId);
        return lecture;
    }
}
