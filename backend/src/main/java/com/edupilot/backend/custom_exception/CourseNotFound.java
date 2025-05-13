package com.edupilot.backend.custom_exception;

public class CourseNotFound extends RuntimeException {

    public CourseNotFound(Long courseId) {
        super("Course with id " + courseId + " not found.");
    }
}
