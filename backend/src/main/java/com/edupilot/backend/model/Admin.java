package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "admin")
public class Admin extends AuditCreation {

    @OneToOne
    private User user;

    @ManyToOne
    private Admin addedBy;
}
