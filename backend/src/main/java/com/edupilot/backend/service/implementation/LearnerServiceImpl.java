package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.LearnerNotFound;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.LearnerRepository;
import com.edupilot.backend.service.interfaces.LearnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LearnerServiceImpl implements LearnerService {

    private final LearnerRepository learnerRepository;

    /**
     * @param learner
     * @return
     */
    @Override
    public Learner save(Learner learner) {

        return learnerRepository.save(learner);
    }

    /**
     * Find learner by id
     *
     * @param id
     * @return
     */
    @Override
    public Learner findLearnerById(Long id) {

        return learnerRepository.findById(id).orElseThrow(() -> new LearnerNotFound(id));
    }

    /**
     * Find learner by user
     *
     * @param user
     * @return
     */
    @Override
    public Learner findLearnerByUser(User user) {

        return learnerRepository.findLearnerByUser(user).orElseThrow(() -> new LearnerNotFound(user.getId()));
    }
}
