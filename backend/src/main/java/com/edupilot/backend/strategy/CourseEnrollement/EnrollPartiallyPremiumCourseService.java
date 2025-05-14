package com.edupilot.backend.strategy.CourseEnrollement;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import org.springframework.stereotype.Service;

@Service("PARTIALLY_PREMIUM")
public class EnrollPartiallyPremiumCourseService implements EnrollCourseService {

    /**
     * Enroll course
     *
     * @param course
     * @param learner
     */
    @Override
    public LearnerCourse enroll(Course course, Learner learner) {

        return null;
    }
}
