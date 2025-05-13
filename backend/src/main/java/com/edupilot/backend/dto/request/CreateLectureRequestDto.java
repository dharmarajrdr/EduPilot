package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Lecture;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateLectureRequestDto {

    private String title;

    private String description;

    private Long courseId;

    private Integer chapterId;

    public Lecture toLecture() {

        return Lecture.builder().title(title).description(description).chapterId(chapterId).build();
    }
}
