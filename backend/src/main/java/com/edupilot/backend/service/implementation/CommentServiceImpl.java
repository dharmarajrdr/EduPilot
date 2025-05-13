package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CommentNotFound;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.EditCommentRequestDto;
import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.model.Comment;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.CommentRepository;
import com.edupilot.backend.service.interfaces.CommentService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

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

    /**
     * Edit the comment
     *
     * @param editCommentRequestDto
     * @param userId
     * @return
     */
    @Override
    public CommentResponseDto editComment(Long commentId, EditCommentRequestDto editCommentRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        Comment comment = editCommentRequestDto.patchComment(findCommentById(commentId));
        if (!comment.getUser().equals(user)) {
            throw new PermissionDenied("You don't have permission to edit this comment.");
        }
        return CommentResponseDto.fromComment(save(comment));
    }
}
