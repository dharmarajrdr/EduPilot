package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import com.edupilot.backend.model.enums.OrderStatus;
import jakarta.persistence.*;
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
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

    @Column(nullable = false)
    private Long price;
}
