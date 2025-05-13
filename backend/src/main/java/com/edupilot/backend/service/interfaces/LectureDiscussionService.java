package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.*;
import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.dto.response.ReplyResponseDto;

import java.util.List;

public interface LectureDiscussionService {

    CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, Long userId);

    void deleteComment(Long commentId, Long userId);

    ReplyResponseDto addReply(AddReplyRequestDto addReplyRequestDto, Long userId);

    void deleteReply(Long replyId, Long userId);

    List<CommentResponseDto> getDiscussions(Long courseId, Long lectureId, Long userId);

    List<ReplyResponseDto> getReplies(Long courseId, Long lectureId, Long commentId, Long userId);
}
