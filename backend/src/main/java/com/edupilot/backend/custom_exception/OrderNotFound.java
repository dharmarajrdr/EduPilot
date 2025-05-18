package com.edupilot.backend.custom_exception;

public class OrderNotFound extends RuntimeException {

    public OrderNotFound(Long orderId) {
        super("Order with id " + orderId + " not found");
    }
}
