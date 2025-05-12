package com.edupilot.backend.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AccessRestriction extends AuditDeletion {

    private Boolean isFree;
}
