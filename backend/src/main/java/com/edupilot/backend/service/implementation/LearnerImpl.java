package com.edupilot.backend.service.implementation;

import com.edupilot.backend.model.Learner;
import com.edupilot.backend.repository.LearnerRepository;
import com.edupilot.backend.service.interfaces.LearnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LearnerImpl implements LearnerService {

    private final LearnerRepository learnerRepository;

    /**
     * @param learner
     * @return
     */
    @Override
    public Learner save(Learner learner) {

        return learnerRepository.save(learner);
    }
}
