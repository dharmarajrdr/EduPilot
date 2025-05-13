package com.edupilot.backend.custom_exception;

public class InstructorNotFound extends RuntimeException {

    public InstructorNotFound(Long instructorId, String property) {
        super("Instructor with " + property + " '" + instructorId + "' not found.");
    }
}
