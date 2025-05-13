package com.edupilot.backend.custom_exception;

public class FeatureNotImplementedYet extends RuntimeException {

    public FeatureNotImplementedYet(String feature) {

        super("Feature not implemented yet: " + feature);
    }
}
