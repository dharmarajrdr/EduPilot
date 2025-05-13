package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditCourseRequestDto {

    private Long id;

    private CourseStatus status;

    private LocalDateTime releasedDate;

    public void patchCourse(Course course) {

        if (this.id == null) {
            throw new IllegalStateException("Course id cannot be null");
        }

        course.setId(this.id);

        if (this.status != null) {
            if (course.getCourseStatus().equals(this.status)) {
                throw new IllegalStateException("Course status was already '" + this.status + "'.");
            }
            course.setCourseStatus(this.status);
        }

        if (this.releasedDate != null && course.getReleasedDate() == null) {
            course.setReleasedDate(this.releasedDate);
        }
    }
}
