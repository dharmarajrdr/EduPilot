package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.enums.LectureAccessMode;
import com.edupilot.backend.model.enums.SubscriptionType;
import lombok.Data;

@Data
public class CreateCourseRequestDto {

    private String title;

    private String description;

    private SubscriptionType subscriptionType;

    private LectureAccessMode lectureAccessMode;

    public Course toCourse() {
        return Course.builder()
                .title(title)
                .description(description)
                .subscriptionType(subscriptionType)
                .lectureAccessMode(lectureAccessMode)
                .build();
    }
}
