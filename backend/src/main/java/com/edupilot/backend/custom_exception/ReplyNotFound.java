package com.edupilot.backend.custom_exception;

public class ReplyNotFound extends RuntimeException {

    public ReplyNotFound(Long replyId) {
        super("Reply with id " + replyId + " not found");
    }
}
