package com.edupilot.backend.custom_exception;

public class LectureNotFound extends RuntimeException {

    public LectureNotFound(Long lectureId, Long courseId) {

        super("Lecture with id " + lectureId + " not found in course with id " + courseId);
    }
}
