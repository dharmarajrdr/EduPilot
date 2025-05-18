package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.response.CreatePaymentLinkResponseDto;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;

public interface EnrollCourseService {

    public CreatePaymentLinkResponseDto enroll(Course course, Learner learner);
}
