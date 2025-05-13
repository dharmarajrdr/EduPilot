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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LectureDiscussionImpl implements LectureDiscussionService {

    private final CommentService commentService;
    private final LectureService lectureService;
    private final LectureDiscussionRepository lectureDiscussionRepository;
    private final ReplyService replyService;
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
     * Get all the comments in the lecture
     *
     * @param comment
     * @return
     */
    public LectureDiscussion findLectureDiscussionByComment(Comment comment) {

        return lectureDiscussionRepository.findLectureDiscussionByComment(comment).orElseThrow(() -> new LectureDiscussionNotFound(comment));
    }

    /**
     * Get the lecture discussion by id
     *
     * @param id
     * @return
     */
    public LectureDiscussion findLectureDiscussionById(Long id) {

        return lectureDiscussionRepository.findLectureDiscussionById(id).orElseThrow(() -> new LectureDiscussionNotFound(id));
    }

    /**
     * Delete the comment from discussion
     *
     * @param commentId
     * @param userId
     */
    @Override
    public void deleteComment(Long commentId, Long userId) {

        User user = userService.findUserById(userId);
        Comment comment = commentService.findCommentById(commentId);
        if (!comment.isAuthor(user)) {
            throw new PermissionDenied("You don't have permission to delete this comment");
        }
        LectureDiscussion lectureDiscussion = findLectureDiscussionByComment(comment);
        lectureDiscussionRepository.delete(lectureDiscussion);  // deleting comments + replies as a whole discussion
    }

    /**
     * Add a reply to a comment
     *
     * @param addReplyRequestDto
     * @param userId
     * @return
     */
    @Override
    public ReplyResponseDto addReply(AddReplyRequestDto addReplyRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        LectureDiscussion lectureDiscussion = findLectureDiscussionById(addReplyRequestDto.getLectureDiscussionId());
        Reply reply = replyService.save(Reply.builder().user(user).message(addReplyRequestDto.getMessage()).lectureDiscussion(lectureDiscussion).build());
        return ReplyResponseDto.fromReply(reply);
    }

    /**
     * Delete the reply
     *
     * @param replyId
     * @param userId
     * @return
     */
    @Override
    public void deleteReply(Long replyId, Long userId) {

        User user = userService.findUserById(userId);
        Reply reply = replyService.findById(replyId);
        if (!reply.isAuthor(user)) {
            throw new PermissionDenied("You don't have permission to delete this reply.");
        }
        replyService.deleteReply(replyId);
    }

    /**
     * Get all the discussions
     *
     * @param courseId
     * @param lectureId
     * @param userId
     * @return
     */
    @Override
    public List<CommentResponseDto> getDiscussions(Long courseId, Long lectureId, Long userId) {

        Lecture lecture = lectureService.findLectureByIdAndCourseId(lectureId, courseId);
        List<LectureDiscussion> lectureDiscussions = lectureDiscussionRepository.findLectureDiscussionsByLectureOrderByIdDesc(lecture);
        List<CommentResponseDto> discussions = new ArrayList<>();
        for (LectureDiscussion discussion : lectureDiscussions) {
            discussions.add(discussion.toCommentResponseDto());
        }
        return discussions;
    }

    /**
     * Get replies for a comment
     *
     * @param courseId
     * @param lectureId
     * @param commentId
     * @param userId
     * @return
     */
    @Override
    public List<ReplyResponseDto> getReplies(Long courseId, Long lectureId, Long commentId, Long userId) {

        Comment comment = commentService.findCommentById(commentId);
        LectureDiscussion lectureDiscussion = findLectureDiscussionByComment(comment);
        List<Reply> replies = lectureDiscussion.getReplies();
        List<ReplyResponseDto> replyResponseDtos = new ArrayList<>();
        for (Reply reply : replies) {
            replyResponseDtos.add(ReplyResponseDto.fromReply(reply));
        }
        return replyResponseDtos;
    }
}
