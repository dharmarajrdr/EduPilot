package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Category;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Tag;
import com.edupilot.backend.model.enums.LectureAccessMode;
import com.edupilot.backend.model.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCourseRequestDto {

    private String title;

    private String description;

    private SubscriptionType subscriptionType;

    private LectureAccessMode lectureAccessMode;

    private String category;

    private List<String> tags;

    private double price = 0.0;

    public Course toCourse() {

        List<Tag> tags = new ArrayList<>();
        if(this.tags != null) {
            for (String tag : this.tags) {
                tags.add(Tag.builder().name(tag).build());
            }
        }
        return Course.builder()
                .title(title)
                .tags(tags)
                .price(price)
                .category(Category.builder().name(category).build())
                .description(description)
                .subscriptionType(subscriptionType)
                .lectureAccessMode(lectureAccessMode)
                .build();
    }
}
