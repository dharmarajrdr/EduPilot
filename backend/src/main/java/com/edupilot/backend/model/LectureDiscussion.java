package com.edupilot.backend.model;

import com.edupilot.backend.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class LectureDiscussion extends BaseModel {

    @OneToOne
    private Comment comment;

    @OneToMany(mappedBy = "lectureDiscussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

    @ManyToOne
    private Lecture lecture;
}
