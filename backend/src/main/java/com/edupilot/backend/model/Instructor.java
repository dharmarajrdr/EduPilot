package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity(name = "instructor")
@EqualsAndHashCode(callSuper = false)
public class Instructor extends AuditCreation {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    @ManyToMany
    @JoinTable(
            name = "instructor_learner_follow",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "learner_id")
    )
    private List<Learner> followers;
}
