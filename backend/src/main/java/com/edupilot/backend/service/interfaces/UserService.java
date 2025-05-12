package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.SignupUserRequestDto;

public interface UserService {

    void signupUser(SignupUserRequestDto signupUserRequestDto);

    Boolean existsByUsername(String username);
}
