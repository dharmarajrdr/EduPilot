package com.edupilot.backend.model.enums;

public enum ResponseStatus {

    SUCCESS, FAILURE;

    public Boolean isSuccess() {
        return this == ResponseStatus.SUCCESS;
    }

    public Boolean isFailure() {
        return this == ResponseStatus.FAILURE;
    }
}
