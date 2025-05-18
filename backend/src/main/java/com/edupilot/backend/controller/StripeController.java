package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.StripeEventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback/v1/stripe")
public class StripeController {

    @PostMapping("/webhook")
    public void callback(StripeEventDto stripeEventDto) {

        // Mark the user as course purchased.
    }
}
