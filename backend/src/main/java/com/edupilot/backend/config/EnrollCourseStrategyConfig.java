package com.edupilot.backend.config;

import com.edupilot.backend.model.enums.SubscriptionType;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import com.edupilot.backend.strategy.CourseEnrollement.EnrollFreeCourseService;
import com.edupilot.backend.strategy.CourseEnrollement.EnrollPartiallyPremiumCourseService;
import com.edupilot.backend.strategy.CourseEnrollement.EnrollPremiumCourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class EnrollCourseStrategyConfig {

    @Bean
    public Map<SubscriptionType, EnrollCourseService> enrollCourseStrategies(EnrollFreeCourseService freeCourseService, EnrollPartiallyPremiumCourseService partiallyPremiumCourseService, EnrollPremiumCourseService premiumCourseService) {

        Map<SubscriptionType, EnrollCourseService> strategies = new EnumMap<>(SubscriptionType.class);
        strategies.put(SubscriptionType.FREE, freeCourseService);
        strategies.put(SubscriptionType.PARTIALLY_PREMIUM, partiallyPremiumCourseService);
        strategies.put(SubscriptionType.PREMIUM, premiumCourseService);
        return strategies;
    }
}

