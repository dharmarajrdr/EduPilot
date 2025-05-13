package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Comment;

public interface CommentService {

    Comment findCommentById(Long commentId);

    Comment save(Comment comment);
}
