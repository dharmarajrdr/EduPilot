package com.edupilot.backend.factory;

import com.edupilot.backend.model.enums.SubscriptionType;
import com.edupilot.backend.service.interfaces.EnrollCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Responsible to generate object based on subscription type of course.
 */
@Component
@RequiredArgsConstructor
public final class EnrollCourseFactory {

    private final Map<SubscriptionType, EnrollCourseService> enrollCourseStrategies;

    public EnrollCourseService getEnrollCourseService(SubscriptionType subscriptionType) {

        EnrollCourseService service = enrollCourseStrategies.get(subscriptionType);
        if (service == null) {
            throw new IllegalArgumentException("Unsupported subscription type: " + subscriptionType);
        }
        return service;
    }
}
