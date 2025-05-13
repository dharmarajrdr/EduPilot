package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.Course;

public interface CourseService {

    CreateCourseResponseDto createCourse(CreateCourseRequestDto createCourseRequestDto, Long userId);

    Course getCourseById(Long courseId);

    void publishCourse(Long courseId, Long userId);

    void archiveCourse(Long courseId, Long userId);
}
