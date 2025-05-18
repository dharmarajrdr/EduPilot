package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import com.edupilot.backend.model.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order extends AuditCreation {

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    @Getter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Getter
    private Long price;
}
