package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LearnerCourse extends AuditCreation {

    @ManyToOne
    private Learner learner;

    @ManyToOne
    private Course course;
}
