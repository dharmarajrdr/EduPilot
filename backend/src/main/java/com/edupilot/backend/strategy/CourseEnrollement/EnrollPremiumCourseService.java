package com.edupilot.backend.strategy.CourseEnrollement;

import com.edupilot.backend.custom_exception.FeatureNotImplementedYet;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import org.springframework.stereotype.Service;

@Service("PREMIUM")
public class EnrollPremiumCourseService implements EnrollCourseService {

    /**
     * Enroll course
     *
     * @param course
     * @param learner
     */
    @Override
    public LearnerCourse enroll(Course course, Learner learner) {

        throw new FeatureNotImplementedYet("Enrolling premium course requires course purchase");
    }
}
