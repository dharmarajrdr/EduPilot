package com.edupilot.backend.model.enums;

public enum UserType {

    LEARNER("Learner"),
    INSTRUCTOR("Instructor"),
    ADMIN("Admin");

    private final String userType;

    UserType(String userType) {

        this.userType = userType;
    }
}
