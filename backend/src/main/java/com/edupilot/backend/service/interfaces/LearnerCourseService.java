package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.LearnerCourse;

public interface LearnerCourseService {

    LearnerCourse enrollCourse(Long courseId, Long userId);;
}
