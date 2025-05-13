package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.Learner;

public class LearnerAlreadyFollowing extends RuntimeException {

    public LearnerAlreadyFollowing(Instructor instructor) {
        super("You are already following instructor with id " + instructor.getId() + ".");
    }
}
