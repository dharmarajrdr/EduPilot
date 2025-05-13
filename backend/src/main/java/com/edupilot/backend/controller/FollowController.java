package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.FollowRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("")
    public ResponseEntity<BaseResponseDto> followInstructor(@RequestBody FollowRequestDto followRequestDto, @RequestParam Long userId) {

        followService.follow(userId, followRequestDto.getUserId());
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().message("Following successfully.").status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @DeleteMapping("")
    public ResponseEntity<BaseResponseDto> unfollowInstructor(@RequestBody FollowRequestDto followRequestDto, @RequestParam Long userId) {

        followService.unfollow(userId, followRequestDto.getUserId());
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().message("Unfollowed successfully.").status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }
}
