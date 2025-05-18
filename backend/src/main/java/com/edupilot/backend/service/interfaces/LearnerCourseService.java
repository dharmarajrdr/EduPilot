package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.response.CreatePaymentLinkResponseDto;

public interface LearnerCourseService {

    CreatePaymentLinkResponseDto enrollCourse(Long courseId, Long userId);

    void purchaseCourse(Long courseId, Long userId);
}
