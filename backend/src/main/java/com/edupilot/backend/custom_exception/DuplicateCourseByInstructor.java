package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Course;

public class DuplicateCourseByInstructor extends RuntimeException {

    public DuplicateCourseByInstructor(Course course) {

        super("Course titled '" + course.getTitle() + "' already exists");
    }
}
