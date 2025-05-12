package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AccessRestriction;
import com.edupilot.backend.model.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Course extends AccessRestriction {

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lecture> lectures;

    @ManyToOne
    private Instructor instructor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType subscriptionType;

    @Column(nullable = false)
    private LocalDateTime releasedDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<LearnerCourse> learnerCourses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<QuizGroup> quizGroups;

}
