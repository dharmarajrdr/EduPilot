package com.edupilot.backend.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
public class CourseOffer {

    @Getter
    private double percentage;

    @Getter
    @Setter
    private LocalDateTime untilDate;

    public void setPercentage(double percentage) {

        this.percentage = percentage % 100;
    }
}
