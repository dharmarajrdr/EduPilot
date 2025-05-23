package com.edupilot.backend.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AuditDeletion extends AuditCreation {

    private LocalDateTime deletionDate;

    private boolean deleted;
}
