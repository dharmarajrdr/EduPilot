package com.edupilot.backend.model;

import com.edupilot.backend.model.base.BaseModel;
import com.edupilot.backend.model.enums.AnswerType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Quiz extends BaseModel {

    @ManyToOne
    private QuizGroup quizGroup;

    private String question;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @ElementCollection
    private List<Option> options;

    @ElementCollection
    private List<Option> answers;
}
