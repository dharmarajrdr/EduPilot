package com.edupilot.backend.strategy.CourseEnrollement;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.repository.LearnerCourseRepository;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollFreeCourseService implements EnrollCourseService {

    private LearnerCourseRepository learnerCourseRepository;

    /**
     * Just enroll the course. That's it!
     *
     * @param course
     * @param learner
     */
    @Override
    public LearnerCourse enroll(Course course, Learner learner) {

        return learnerCourseRepository.save(new LearnerCourse(learner, course));
    }
}
