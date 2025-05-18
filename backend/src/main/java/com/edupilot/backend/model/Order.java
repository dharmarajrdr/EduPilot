package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import com.edupilot.backend.model.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Builder
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order extends AuditCreation {

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long price;
}
