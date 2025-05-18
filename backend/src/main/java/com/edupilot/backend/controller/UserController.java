package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.SignupUserRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponseDto> signupUser(@RequestBody SignupUserRequestDto signupUserRequestDto) {

        userService.signupUser(signupUserRequestDto);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).message("Account created successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

}
