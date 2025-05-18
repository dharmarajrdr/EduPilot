package com.edupilot.backend.service.implementation;

import com.edupilot.backend.model.Course;
import org.springframework.stereotype.Service;

import com.edupilot.backend.machine_learning.PaymentServiceAnalyser;
import com.edupilot.backend.model.Order;
import com.edupilot.backend.model.enums.PaymentType;
import com.edupilot.backend.registry.PaymentServiceRegistry;
import com.edupilot.backend.service.interfaces.OrderService;
import com.edupilot.backend.service.interfaces.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl {

    private final PaymentServiceAnalyser paymentServiceAnalyser;
    private final PaymentServiceRegistry paymentServiceRegistry;
    private final OrderService orderService;

    /**
     * Generate the payment link based on ML analyser
     *
     * @param orderId
     * @return String
     */
    public String createPaymentLink(Long orderId) throws Exception {

        // 1. Get order info from orderService
        Order order = orderService.getOrderById(orderId);
        Course course = order.getCourse();
        Long price = order.getPrice();

        // 2. Get better performant payment service
        PaymentType paymentType = paymentServiceAnalyser.getBetterPerformantPaymentType();

        // 3. Get payment service object based on strategy
        PaymentService paymentService = paymentServiceRegistry.getPaymentService(paymentType);

        // 4. Create payment link
        return paymentService.createPaymentLink(course);
    }
}
