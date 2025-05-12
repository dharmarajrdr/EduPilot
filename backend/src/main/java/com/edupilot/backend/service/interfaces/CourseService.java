package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;

public interface CourseService {

    CreateCourseResponseDto createCourse(CreateCourseRequestDto createCourseRequestDto, Long userId);
}
