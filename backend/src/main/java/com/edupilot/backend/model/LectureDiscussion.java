package com.edupilot.backend.model;

import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LectureDiscussion extends BaseModel {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Comment comment;

    @OneToMany(mappedBy = "lectureDiscussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lecture lecture;

    public CommentResponseDto toCommentResponseDto() {

        return CommentResponseDto.builder().message(comment.getMessage()).userName(comment.getUser().getUserName()).createdAt(getComment().getCreatedAt()).discussionId(getId()).build();
    }
}
