package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.User;

public interface LearnerService {

    public Learner save(Learner learner);

    public Learner findLearnerById(Long id);

    public Learner findLearnerByUser(User user);
}
