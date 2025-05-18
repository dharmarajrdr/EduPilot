package com.edupilot.backend.strategy.Payment;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentService implements PaymentService {

    /**
     * @param course
     * @return
     */
    @Override
    public String createPaymentLink(Course course) {
        return "";
    }
}
