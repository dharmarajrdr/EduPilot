package com.edupilot.backend.strategy.CourseEnrollement;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.repository.LearnerCourseRepository;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("PARTIALLY_PREMIUM")
@AllArgsConstructor
public class EnrollPartiallyPremiumCourseService implements EnrollCourseService {

    private final LearnerCourseRepository learnerCourseRepository;

    /**
     * Enroll course
     *
     * @param course
     * @param learner
     */
    @Override
    public LearnerCourse enroll(Course course, Learner learner) {

        return learnerCourseRepository.save(new LearnerCourse(learner, course));
    }
}
