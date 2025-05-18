package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
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
public class Course extends AuditCreation {

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

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

    @Column(nullable = false)
    @Builder.Default
    private Double price = 0.0;

    private CourseOffer courseOffer;

    @ManyToMany
    @JoinTable(
            name = "course_tag",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public boolean isOwner(User user) {

        return instructor.getUser().equals(user);
    }

    public long getDiscountAppliedPrice() {

        if (courseOffer == null) {
            return Long.parseLong(price.toString());
        }

        LocalDateTime discountUntil = courseOffer.getUntilDate();
        if (discountUntil == null || discountUntil.isAfter(LocalDateTime.now())) {
            return Long.parseLong(price.toString());
        }

        double discountPercent = courseOffer.getPercentage();
        double discountPrice = (discountPercent / 100) * price;
        double priceAfterDiscount = price - discountPrice;
        return Long.parseLong(Double.toString(priceAfterDiscount));
    }

}
