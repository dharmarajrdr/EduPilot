package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditDeletion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Course extends AuditDeletion {

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture> lectures;

    @ManyToOne
    private Instructor instructor;

    @Column(nullable = false)
    private LocalDateTime releasedDate;
}
