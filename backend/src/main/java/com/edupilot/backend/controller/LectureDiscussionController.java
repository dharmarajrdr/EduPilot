package com.edupilot.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edupilot.backend.dto.request.AddReplyRequestDto;
import com.edupilot.backend.dto.request.CreateCommentRequestDto;
import com.edupilot.backend.dto.request.EditCommentRequestDto;
import com.edupilot.backend.dto.request.EditReplyRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.dto.response.CommentResponseDto;
import com.edupilot.backend.dto.response.ReplyResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.CommentService;
import com.edupilot.backend.service.interfaces.LectureDiscussionService;
import com.edupilot.backend.service.interfaces.ReplyService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/discussion")
public class LectureDiscussionController {

    private final CommentService commentService;
    private final LectureDiscussionService lectureDiscussionService;
    private final ReplyService replyService;

    @PostMapping("/comment")
    public ResponseEntity<BaseResponseDto> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto, @RequestParam Long userId) {

        CommentResponseDto commentResponseDto = lectureDiscussionService.createComment(createCommentRequestDto, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(commentResponseDto).message("Comment created successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<BaseResponseDto> deleteComment(@PathVariable Long commentId, @RequestParam Long userId) {

        lectureDiscussionService.deleteComment(commentId, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).message("Comment deleted successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @PatchMapping("/comment/{commentId}")
    public ResponseEntity<BaseResponseDto> editComment(@PathVariable Long commentId, @RequestBody EditCommentRequestDto editCommentRequestDto, @RequestParam Long userId) {

        CommentResponseDto commentResponseDto = commentService.editComment(commentId, editCommentRequestDto, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(commentResponseDto).message("Comment updated successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @PostMapping("/reply")
    public ResponseEntity<BaseResponseDto> addReply(@RequestBody AddReplyRequestDto addReplyRequestDto, @RequestParam Long userId) {

        ReplyResponseDto replyResponseDto = lectureDiscussionService.addReply(addReplyRequestDto, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(replyResponseDto).message("Reply created successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<BaseResponseDto> deleteReply(@PathVariable Long replyId, @RequestParam Long userId) {

        lectureDiscussionService.deleteReply(replyId, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).message("Reply deleted successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @PatchMapping("/reply/{replyId}")
    public ResponseEntity<BaseResponseDto> editReply(@PathVariable Long replyId, @RequestBody EditReplyRequestDto editReplyRequestDto, @RequestParam Long userId) {

        ReplyResponseDto replyResponseDto = replyService.editReply(replyId, editReplyRequestDto, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(replyResponseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @GetMapping("/{courseId}/{lectureId}")
    public ResponseEntity<BaseResponseDto> getDiscussions(@PathVariable Long courseId, @PathVariable Long lectureId, @RequestParam Long userId) {

        List<CommentResponseDto> comments = lectureDiscussionService.getDiscussions(courseId, lectureId, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(comments).message("Discussions retrieved successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }

    @GetMapping("/{courseId}/{lectureId}/{commentId}")
    public ResponseEntity<BaseResponseDto> getReplies(@PathVariable Long courseId, @PathVariable Long lectureId, @PathVariable Long commentId, @RequestParam Long userId) {

        List<ReplyResponseDto> replies = lectureDiscussionService.getReplies(courseId, lectureId, commentId, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().status(ResponseStatus.SUCCESS).data(replies).message("Replies retrieved successfully.").build();
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDto);
    }
}
