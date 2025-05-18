package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Course;

public class CourseStripeNotFound extends RuntimeException {

    public CourseStripeNotFound(Course course) {
        super("Product not found for the course '" + course.getTitle() + "' in stripe.");
    }
}
