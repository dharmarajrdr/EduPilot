package com.edupilot.backend.custom_exception;

public class LearnerNotFound extends RuntimeException {

    public LearnerNotFound(Long learnerId) {
        super("Learner with id " + learnerId + " not found");
    }
}
