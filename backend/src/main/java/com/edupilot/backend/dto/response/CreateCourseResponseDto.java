package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseResponseDto {

    private String title;

    private String description;

    public static CreateCourseResponseDto fromCourse(Course course) {

        return CreateCourseResponseDto.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .build();
    }
}
