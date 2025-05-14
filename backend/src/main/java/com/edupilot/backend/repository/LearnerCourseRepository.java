package com.edupilot.backend.repository;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerCourseRepository extends JpaRepository<LearnerCourse, Long> {

    boolean existsByCourseAndLearner(Course course, Learner learner);
}
