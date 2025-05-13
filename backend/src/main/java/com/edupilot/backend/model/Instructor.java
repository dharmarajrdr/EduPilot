package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "instructor")
@EqualsAndHashCode(callSuper = false)
public class Instructor extends AuditCreation {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    private String currentDesignation;

    private String bio;

    @OneToMany(mappedBy = "instructor")
    private List<Skill> skills;

    @OneToMany
    private List<Rating> ratings;;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "instructor_learner_follow",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "learner_id")
    )
    private List<Learner> followers;
}
