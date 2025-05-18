package com.edupilot.backend.repository;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.CourseStripe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseStripeRepository extends JpaRepository<CourseStripe, Long> {

    Optional<CourseStripe> findByCourse(Course course);
}
