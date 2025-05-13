package com.edupilot.backend.custom_exception;

public class CommentNotFound extends RuntimeException {

    public CommentNotFound(Long commentId) {
        super("Comment with id " + commentId + " not found");
    }
}
