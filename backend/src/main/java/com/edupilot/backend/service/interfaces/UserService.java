package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.SignupUserRequestDto;
import com.edupilot.backend.model.User;

public interface UserService {

    void signupUser(SignupUserRequestDto signupUserRequestDto);

    Boolean existsByUsername(String username);

    public User findUserById(Long userId);

    public Boolean isInstructor(Long userId);

    public Boolean isAdmin(Long userId);

    public Boolean isLearner(Long userId);
}
