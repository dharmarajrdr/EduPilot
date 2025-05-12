package com.edupilot.backend.custom_exception;

public class PermissionDenied extends RuntimeException {

    public PermissionDenied(String message) {

        super(message);
    }
}
