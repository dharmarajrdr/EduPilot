package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AccessRestriction;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class QuizGroup extends AccessRestriction {

    @OneToMany(mappedBy = "quizGroup")
    private List<Quiz> quizzes;

    private String title;

    @ManyToOne
    private Course course;
}
