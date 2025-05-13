package com.edupilot.backend.repository;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsCourseByTitleAndInstructor(String title, Instructor instructor);

    Optional<Course> findCourseById(Long courseId);
}
