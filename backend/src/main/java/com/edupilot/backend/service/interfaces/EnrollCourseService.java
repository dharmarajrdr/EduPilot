package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;

public interface EnrollCourseService {

    public LearnerCourse enroll(Course course, Learner learner);
}
