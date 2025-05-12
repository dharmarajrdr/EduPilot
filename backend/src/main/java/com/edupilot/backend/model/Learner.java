package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AccessRestriction;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Learner extends AccessRestriction {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "learner")
    private List<LearnerCourse> courses;

    @OneToMany(mappedBy = "learner")
    private List<LearnerLecture> lectures;

    @ManyToMany(mappedBy = "followers")
    private List<Instructor> following;
}
