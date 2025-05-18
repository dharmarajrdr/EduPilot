package com.edupilot.backend.registry;

import com.edupilot.backend.model.enums.PaymentType;
import com.edupilot.backend.service.interfaces.PaymentService;
import com.edupilot.backend.strategy.Payment.RazorpayPaymentService;
import com.edupilot.backend.strategy.Payment.StripePaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceRegistry {

    private final StripePaymentService stripePaymentService;
    private final RazorpayPaymentService razorpayPaymentService;

    public PaymentService getPaymentService(PaymentType paymentType) {

        return switch (paymentType) {
            case STRIPE -> stripePaymentService;
            case RAZORPAY -> razorpayPaymentService;
        };
    }
}
