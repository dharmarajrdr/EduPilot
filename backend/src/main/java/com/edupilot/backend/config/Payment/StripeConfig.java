package com.edupilot.backend.config.Payment;

import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.key}")
    private String STRIPE_API_KEY;

    @Bean
    public StripeClient getStripeClient() {

        return new StripeClient(STRIPE_API_KEY);
    }
}
