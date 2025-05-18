package com.edupilot.backend.strategy.Payment;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.CourseStripe;
import com.edupilot.backend.service.interfaces.CourseStripeService;
import com.edupilot.backend.service.interfaces.PaymentService;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StripePaymentService implements PaymentService {

    private final CourseStripeService courseStripeService;

    /** Creates payment link
     * @param course
     * @return String
     */
    @Override
    public String createPaymentLink(Course course) throws Exception {

        CourseStripe courseStripe = courseStripeService.findByCourse(course);

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .setCurrency("inr")
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(courseStripe.getPriceId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        return PaymentLink.create(params).getUrl();
    }

}
