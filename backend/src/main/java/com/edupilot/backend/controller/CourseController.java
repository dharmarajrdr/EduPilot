package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.request.EditCourseRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.enums.CourseStatus;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("")
    public ResponseEntity<BaseResponseDto> createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto, @RequestParam Long userId) {

        CreateCourseResponseDto createCourseResponseDto = courseService.createCourse(createCourseRequestDto, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder()
                .message("Course created")
                .status(ResponseStatus.SUCCESS)
                .data(createCourseResponseDto)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/publish/{courseId}")
    public ResponseEntity<BaseResponseDto> publishCourse(@PathVariable Long courseId, @RequestParam Long userId) {

        EditCourseRequestDto editCourseRequestDto = EditCourseRequestDto.builder()
                .id(courseId)
                .status(CourseStatus.PUBLISHED)
                .releasedDate(LocalDateTime.now())
                .build();
        courseService.patchCourse(editCourseRequestDto, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder()
                .message("Course published successfully.")
                .status(ResponseStatus.SUCCESS)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/archive/{courseId}")
    public ResponseEntity<BaseResponseDto> archiveCourse(@PathVariable Long courseId, @RequestParam Long userId) {

        EditCourseRequestDto editCourseRequestDto = EditCourseRequestDto.builder()
                .id(courseId)
                .status(CourseStatus.ARCHIVED)
                .build();
        courseService.patchCourse(editCourseRequestDto, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder()
                .message("Course archived successfully.")
                .status(ResponseStatus.SUCCESS)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
