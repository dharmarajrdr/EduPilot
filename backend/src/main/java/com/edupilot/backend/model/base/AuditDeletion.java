package com.edupilot.backend.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuditDeletion extends AuditCreation {

    private LocalDateTime deletionDate;

    private boolean deleted;
}
