package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "learner")
public class Learner extends AuditCreation {

    @OneToOne
    private User user;

    @OneToMany
    private List<LectureDiscussion> lectureDiscussions;
}
