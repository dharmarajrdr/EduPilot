package com.edupilot.backend.custom_exception;

public class UserNotFound extends RuntimeException {

    public UserNotFound(Long userId) {
        super("User with id " + userId + " not found");
    }
}
