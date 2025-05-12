package com.edupilot.backend.model.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditDeletion extends AuditCreation {

    private LocalDateTime deletionDate;

    private boolean deleted;
}
