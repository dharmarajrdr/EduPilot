package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Course;

public interface PaymentService {

    public String createPaymentLink(Course course) throws Exception;
}
