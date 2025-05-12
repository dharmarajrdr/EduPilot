package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<BaseResponseDto> createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto, @RequestParam Long userId) {

        CreateCourseResponseDto createCourseResponseDto = courseService.createCourse(createCourseRequestDto, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder()
                .message("Course created")
                .status(ResponseStatus.SUCCESS)
                .data(createCourseResponseDto)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
