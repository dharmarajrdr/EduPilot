package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditDeletion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Lecture extends AuditDeletion {

    @Column(nullable = false)
    private Integer chapterId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private UUID videoId;

    @ElementCollection
    private List<LectureAttachment> attachments;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureDiscussion> lectureDiscussions;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "lecture")
    private List<LearnerLecture> learnerLectures;

}
