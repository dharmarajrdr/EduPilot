package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AccessRestriction;
import com.edupilot.backend.model.enums.CourseStatus;
import com.edupilot.backend.model.enums.LectureAccessMode;
import com.edupilot.backend.model.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private SubscriptionType subscriptionType = SubscriptionType.FREE;

    private LocalDateTime releasedDate;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus = CourseStatus.DRAFT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LectureAccessMode lectureAccessMode = LectureAccessMode.RANDOM;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<LearnerCourse> learnerCourses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<QuizGroup> quizGroups;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Certificate> certificates;

}
