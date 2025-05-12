package com.edupilot.backend.custom_exception;

public class NewAdminCreationRestricted extends RuntimeException {

    public NewAdminCreationRestricted() {

        super("Unable to create an account as Administrator.");
    }
}
