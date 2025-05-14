package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.RejectInstructorRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/admin")
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
