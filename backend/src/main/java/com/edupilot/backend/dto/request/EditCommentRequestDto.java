package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Comment;
import lombok.Data;

@Data
public class EditCommentRequestDto {

    private String message;

    public Comment patchComment(Comment comment) {

        comment.setMessage(message);
        return comment;
    }
}
