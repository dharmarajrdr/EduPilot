package com.edupilot.backend.custom_exception;

public class CourseAlreadyEnrolled extends RuntimeException {

    public CourseAlreadyEnrolled() {
        super("You have already enrolled in this course.");
    }
}
