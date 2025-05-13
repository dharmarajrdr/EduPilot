package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.EditCommentRequestDto;
import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.model.Comment;

public interface CommentService {

    Comment findCommentById(Long commentId);

    Comment save(Comment comment);

    CommentResponseDto editComment(Long commentId, EditCommentRequestDto editCommentRequestDto, Long userId);
}
