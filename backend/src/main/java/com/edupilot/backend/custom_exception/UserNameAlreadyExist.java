package com.edupilot.backend.custom_exception;

public class UserNameAlreadyExist extends RuntimeException {

    public UserNameAlreadyExist(String userName) {

        super("Username '" + userName + "' already exist.");
    }
}
