package com.edupilot.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edupilot.backend.dto.request.RejectInstructorRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.AdminService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/instructor/{instructorId}/approve")
    public ResponseEntity<BaseResponseDto> approveInstructor(@PathVariable Long instructorId, @RequestParam Long userId) {

        adminService.approveInstructor(instructorId, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).message("Approved the instructor").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @DeleteMapping("/instructor/{instructorId}/reject")
    public ResponseEntity<BaseResponseDto> approveInstructor(@PathVariable Long instructorId, @RequestBody RejectInstructorRequestDto rejectInstructorRequestDto, @RequestParam Long userId) {

        adminService.rejectInstructor(instructorId, userId, rejectInstructorRequestDto);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).message("Rejected the instructor").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }
}
