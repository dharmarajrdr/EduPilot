package com.edupilot.backend.strategy.CourseEnrollement;

import com.edupilot.backend.dto.response.CreatePaymentLinkResponseDto;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.Order;
import com.edupilot.backend.service.implementation.PaymentServiceImpl;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import com.edupilot.backend.service.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("PARTIALLY_PREMIUM")
@AllArgsConstructor
public class EnrollPartiallyPremiumCourseService implements EnrollCourseService {

    private final OrderService orderService;
    private final PaymentServiceImpl paymentServiceImpl;

    /**
     * Enroll course
     *
     * @param course
     * @param learner
     */
    @Override
    public CreatePaymentLinkResponseDto enroll(Course course, Learner learner) {

        try {
            Order order = orderService.placeOrder(course, learner.getUser());
            String url = paymentServiceImpl.createPaymentLink(order.getId());
            return CreatePaymentLinkResponseDto.builder()
                    .paymentRequired(true)
                    .url(url)
                    .message("Payment link created.")
                    .orderId(order.getId())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
