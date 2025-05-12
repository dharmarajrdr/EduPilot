package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditDeletion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Lecture extends AuditDeletion {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private UUID videoId;

    @ElementCollection
    private List<LectureAttachment> attachments;

    @OneToMany(mappedBy = "lecture")
    private List<LectureDiscussion> discussions;
}
