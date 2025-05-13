package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Course;

public class ChapterAlreadyExist extends RuntimeException {

    public ChapterAlreadyExist(Integer chapterId, Course course) {
        super("Chapter with id " + chapterId + " already exist in course '" + course.getTitle() + "'.");
    }
}
