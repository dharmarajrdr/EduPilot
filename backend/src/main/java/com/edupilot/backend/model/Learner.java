package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AccessRestriction;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Learner extends AccessRestriction {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "learner")
    private List<LearnerCourse> courses;

    @OneToMany(mappedBy = "learner")
    private List<LearnerLecture> lectures;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private List<Instructor> following;

    @OneToMany(mappedBy = "learner")
    private List<Certificate> certificates;
}
