package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
/**
 * This table is specific to `Stripe` integration
 */
public class CourseStripe extends AuditCreation {

    @OneToOne
    private Course course;

    private String productId;

    private String priceId;
}
