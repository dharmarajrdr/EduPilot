package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.LectureDiscussionNotFound;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.*;
import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.dto.response.ReplyResponseDto;
import com.edupilot.backend.model.*;
import com.edupilot.backend.repository.LectureDiscussionRepository;
import com.edupilot.backend.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LectureDiscussionImpl implements LectureDiscussionService {

    private final CommentService commentService;
    private final LectureService lectureService;
    private final LectureDiscussionRepository lectureDiscussionRepository;
    private final UserService userService;

    /**
     * Create a new comment
     *
     * @param createCommentRequestDto
     * @param userId
     * @return
     */
    @Override
    public CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        Lecture lecture = lectureService.findLectureByIdAndCourseId(createCommentRequestDto.getLectureId(), createCommentRequestDto.getCourseId());
        Comment comment = Comment.builder().user(user).message(createCommentRequestDto.getMessage()).build();
        LectureDiscussion lectureDiscussion = lectureDiscussionRepository.save(LectureDiscussion.builder().lecture(lecture).comment(comment).build());
        CommentResponseDto commentResponseDto = CommentResponseDto.fromComment(comment);
        commentResponseDto.setDiscussionId(lectureDiscussion.getId());
        return commentResponseDto;
    }

    /**
     * @param comment
     * @return
     */
    public LectureDiscussion findLectureDiscussionByComment(Comment comment) {

        return lectureDiscussionRepository.findLectureDiscussionByComment(comment).orElseThrow(() -> new LectureDiscussionNotFound(comment));
    }

    /**
     * Delete the comment from discussion
     *
     * @param commentId
     * @param deleteCommentRequestDto
     * @param userId
     * @return
     */
    @Override
    public boolean deleteComment(Long commentId, DeleteCommentRequestDto deleteCommentRequestDto, Long userId) {

        User user = userService.findUserById(userId);
//        Course course = courseService.getCourseById(deleteCommentRequestDto.getCourseId());
//        Lecture lecture = lectureService.findLectureByIdAndCourseId(deleteCommentRequestDto.getLectureId(), deleteCommentRequestDto.getCourseId());
        Comment comment = commentService.findCommentById(commentId);
        if (!comment.getUser().equals(user)) {
            throw new PermissionDenied("You don't have permission to delete this comment");
        }
        LectureDiscussion lectureDiscussion = findLectureDiscussionByComment(comment);
        lectureDiscussionRepository.delete(lectureDiscussion);
        return true;
    }

    /**
     * @param addReplyRequestDto
     * @param userId
     * @return
     */
    @Override
    public ReplyResponseDto addReply(AddReplyRequestDto addReplyRequestDto, Long userId) {
        return null;
    }

    /**
     * Delete the reply
     *
     * @param deleteReplyRequestDto
     * @param userId
     * @return
     */
    @Override
    public boolean deleteReply(DeleteReplyRequestDto deleteReplyRequestDto, Long userId) {
        return false;
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
        Comment comment = editCommentRequestDto.patchComment(commentService.findCommentById(commentId));
        if (!comment.getUser().equals(user)) {
            throw new PermissionDenied("You don't have permission to edit this comment.");
        }
        return CommentResponseDto.fromComment(commentService.save(comment));
    }

    /**
     * @param editReplyRequestDto
     * @param userId
     * @return
     */
    @Override
    public ReplyResponseDto editReply(EditReplyRequestDto editReplyRequestDto, Long userId) {
        return null;
    }

    /**
     * @param courseId
     * @param lectureId
     * @param userId
     * @return
     */
    @Override
    public List<CommentResponseDto> getDiscussions(Long courseId, Long lectureId, Long userId) {
        return List.of();
    }

    /**
     * @param courseId
     * @param lectureId
     * @param commentId
     * @param userId
     * @return
     */
    @Override
    public List<ReplyResponseDto> getReplies(Long courseId, Long lectureId, Long commentId, Long userId) {
        return List.of();
    }
}
