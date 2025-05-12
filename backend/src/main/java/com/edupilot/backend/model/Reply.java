package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reply extends AuditCreation {

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    private LectureDiscussion lectureDiscussion;
}
