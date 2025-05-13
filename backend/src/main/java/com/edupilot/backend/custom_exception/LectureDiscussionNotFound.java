package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Comment;

public class LectureDiscussionNotFound extends RuntimeException {

    public LectureDiscussionNotFound(Comment comment) {
        super("Lecture Discussion with comment id " + comment.getId() + " not found");
    }

    public LectureDiscussionNotFound(Long id) {
        super("Lecture Discussion with id " + id + " not found");
    }
}
