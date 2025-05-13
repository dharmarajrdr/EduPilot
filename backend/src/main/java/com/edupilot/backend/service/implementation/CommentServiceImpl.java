package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CommentNotFound;
import com.edupilot.backend.model.Comment;
import com.edupilot.backend.repository.CommentRepository;
import com.edupilot.backend.service.interfaces.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    /**
     * Get the comment
     *
     * @param commentId
     * @return
     */
    @Override
    public Comment findCommentById(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFound(commentId));
    }

    /**
     * Same comment
     *
     * @param comment
     * @return
     */
    @Override
    public Comment save(Comment comment) {

        return commentRepository.save(comment);
    }
}
