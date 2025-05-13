package com.edupilot.backend.repository;

import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {

    Optional<Learner> findLearnerByUser(User user);
}
