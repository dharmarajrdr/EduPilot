package com.edupilot.backend.custom_exception;

public class AccountAlreadyRegistered extends RuntimeException {

    public AccountAlreadyRegistered() {

        super("Email or phone number already registered with other account.");
    }
}
