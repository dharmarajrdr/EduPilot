package com.edupilot.backend.custom_exception;

public class TagNotFound extends RuntimeException {

    public TagNotFound(String tagName) {

        super("Tag '" + tagName + "' not found");
    }
}
