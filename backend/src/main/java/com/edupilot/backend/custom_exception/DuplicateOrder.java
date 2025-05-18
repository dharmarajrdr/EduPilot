package com.edupilot.backend.custom_exception;

public class DuplicateOrder extends RuntimeException {

    public DuplicateOrder() {

        super("Order already placed. Please try again later.");
    }
}
