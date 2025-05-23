package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.CourseService;
import com.edupilot.backend.service.interfaces.LearnerCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/course")
public class CourseController {

    private final CourseService courseService;
    private final LearnerCourseService learnerCourseService;

    @PostMapping("")
    public ResponseEntity<BaseResponseDto> createCourse(@RequestBody CreateCourseRequestDto createCourseRequestDto, @RequestParam Long userId) {

        CreateCourseResponseDto createCourseResponseDto = courseService.createCourse(createCourseRequestDto, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder().message("Course created").status(ResponseStatus.SUCCESS).data(createCourseResponseDto).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{courseId}/publish")
    public ResponseEntity<BaseResponseDto> publishCourse(@PathVariable Long courseId, @RequestParam Long userId) {

        courseService.publishCourse(courseId, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder().message("Course published successfully.").status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{courseId}/archive")
    public ResponseEntity<BaseResponseDto> archiveCourse(@PathVariable Long courseId, @RequestParam Long userId) {

        courseService.archiveCourse(courseId, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder().message("Course archived successfully.").status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<BaseResponseDto> enrollCourse(@PathVariable Long courseId, @RequestParam Long userId) {

        LearnerCourse learnerCourse = learnerCourseService.enrollCourse(courseId, userId);
        BaseResponseDto responseDto = BaseResponseDto.builder().message("Course enrolled successfully.").status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
