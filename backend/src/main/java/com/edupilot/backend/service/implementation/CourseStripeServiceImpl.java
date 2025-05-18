package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CourseStripeNotFound;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.CourseStripe;
import com.edupilot.backend.repository.CourseStripeRepository;
import com.edupilot.backend.service.interfaces.CourseStripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseStripeServiceImpl implements CourseStripeService {

    private final CourseStripeRepository courseStripeRepository;

    /**
     * Create product in stripe
     *
     * @param course
     * @return
     * @throws StripeException
     */
    @Override
    public Product productCreation(Course course) throws StripeException {

        ProductCreateParams params = ProductCreateParams.builder()
                .setDescription("EduPilot | Course by - " + course.getInstructor().getUser().getUserName())
                .setName(course.getTitle())
                .setActive(true)
                .setDefaultPriceData(
                        ProductCreateParams.DefaultPriceData.builder()
                                .setCurrency("inr")
                                .setUnitAmount(Long.parseLong(course.getPrice().toString()))
                                .build()
                ).build();

        return Product.create(params);
    }

    /**
     * Create price in stripe
     *
     * @param product
     * @return Price
     * @throws StripeException
     */
    @Override
    public Price priceCreation(Product product) throws StripeException {

        PriceCreateParams params = PriceCreateParams.builder()
                .setCurrency("inr")
                .setUnitAmount(Long.parseLong(product.getDefaultPrice()))
                .setProduct(product.getId())
                .build();

        return Price.create(params);
    }

    /**
     * Creates Stripe payment link for the give course
     *
     * @param course
     * @return String
     * @throws StripeException
     */
    @Override
    public String createPaymentLink(Course course) throws StripeException {

        CourseStripe courseStripe = findByCourse(course);
        String priceId = courseStripe.getPriceId();

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .setCurrency("inr")
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(priceId)
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getId();
    }

    /**
     * Get the CourseStripe record
     *
     * @param course
     * @return CourseStripe
     */
    @Override
    public CourseStripe findByCourse(Course course) {

        return courseStripeRepository.findByCourse(course).orElseThrow(() -> new CourseStripeNotFound(course));
    }

    /**
     * Create product and price at the time of course publication
     *
     * @param course
     * @return CourseStripe
     * @throws StripeException
     */
    @Override
    public CourseStripe save(Course course) throws StripeException {

        Product product = productCreation(course);
        Price price = priceCreation(product);
        CourseStripe courseStripe = CourseStripe.builder().course(course).productId(product.getId()).priceId(price.getId()).build();
        return courseStripeRepository.save(courseStripe);
    }
}
