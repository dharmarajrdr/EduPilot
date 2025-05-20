package com.edupilot.backend.custom_exception;

public class CategoryNotFound extends RuntimeException {

    public CategoryNotFound(Long categoryId) {
        super("Category with id " + categoryId + " not found");
    }
}
