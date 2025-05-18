package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.CourseStripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;

public interface CourseStripeService {

    public Product productCreation(Course course) throws StripeException;

    public Price priceCreation(Product product) throws StripeException;

    public String createPaymentLink(Course course) throws StripeException;

    public CourseStripe findByCourse(Course course);

    public CourseStripe save(Course course) throws StripeException;
}
