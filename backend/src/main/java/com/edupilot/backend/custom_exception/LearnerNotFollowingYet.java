package com.edupilot.backend.custom_exception;

import com.edupilot.backend.model.Instructor;

public class LearnerNotFollowingYet extends RuntimeException {

    public LearnerNotFollowingYet(Instructor instructor) {

        super("You are not following instructor with id " + instructor.getUser().getId() + " yet");
    }
}
