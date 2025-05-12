package com.edupilot.backend.model.base;

import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditCreation extends BaseModel {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
