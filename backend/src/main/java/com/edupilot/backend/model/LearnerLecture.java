package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class LearnerLecture extends AuditCreation {

    @ManyToOne
    private Learner learner;

    @ManyToOne
    private Lecture lecture;
}
