package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.LearnerAlreadyFollowing;
import com.edupilot.backend.custom_exception.LearnerNotFollowingYet;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.repository.InstructorRepository;
import com.edupilot.backend.service.interfaces.FollowService;
import com.edupilot.backend.service.interfaces.InstructorService;
import com.edupilot.backend.service.interfaces.LearnerService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final InstructorRepository instructorRepository;
    private final InstructorService instructorService;
    private final LearnerService learnerService;
    private final UserService userService;

    /**
     * Learner follow instructor
     *
     * @param learnerId
     * @param instructorId
     */
    @Override
    public void follow(Long learnerId, Long instructorId) {

        Instructor instructor = instructorService.findInstructorByUser(userService.findUserById(instructorId));
        Learner learner = learnerService.findLearnerByUser(userService.findUserById(learnerId));
        List<Learner> followers = instructor.getFollowers();
        boolean alreadyFollowing = followers.contains(learner);
        if (alreadyFollowing) {
            throw new LearnerAlreadyFollowing(instructor);
        }
        followers.add(learner);
        instructorRepository.save(instructor);
    }

    /**
     * Learner unfollow instructor
     *
     * @param learnerId
     * @param instructorId
     */
    @Override
    public void unfollow(Long learnerId, Long instructorId) {

        Instructor instructor = instructorService.findInstructorByUser(userService.findUserById(instructorId));
        Learner learner = learnerService.findLearnerByUser(userService.findUserById(learnerId));
        List<Learner> followers = instructor.getFollowers();
        boolean alreadyFollowing = followers.contains(learner);
        if (!alreadyFollowing) {
            throw new LearnerNotFollowingYet(instructor);
        }
        followers.remove(learner);
        instructorRepository.save(instructor);
    }
}
