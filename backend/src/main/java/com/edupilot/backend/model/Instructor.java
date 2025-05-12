package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
}
