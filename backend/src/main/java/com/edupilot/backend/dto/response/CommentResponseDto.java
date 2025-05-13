package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto {

    private String userName;

    private String message;

    private Long discussionId;

    private LocalDateTime createdAt;

    public static CommentResponseDto fromComment(Comment comment) {
        return CommentResponseDto.builder()
                .message(comment.getMessage())
                .createdAt(comment.getCreatedAt())
                .userName(comment.getUser().getUserName())
                .build();
    }
}
