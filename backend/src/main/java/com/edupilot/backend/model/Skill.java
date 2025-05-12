package com.edupilot.backend.model;

import com.edupilot.backend.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Skill extends BaseModel {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Instructor instructor;
}
