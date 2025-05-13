package com.edupilot.backend.service.interfaces;

public interface FollowService {

    void follow(Long learnerId, Long instructorId);

    void unfollow(Long learnerId, Long instructorId);
}
