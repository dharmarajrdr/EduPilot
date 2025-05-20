package com.edupilot.backend.util;

import com.edupilot.backend.custom_exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyRegistered.class)
    public ResponseEntity<BaseResponseDto> accountAlreadyRegistered(AccountAlreadyRegistered e) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(PermissionDenied.class)
    public ResponseEntity<BaseResponseDto> permissionDenied(PermissionDenied e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(InstructorNotFound.class)
    public ResponseEntity<BaseResponseDto> instructorNotFound(InstructorNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<BaseResponseDto> userNotFound(UserNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(UserNameAlreadyExist.class)
    public ResponseEntity<BaseResponseDto> userNameAlreadyExist(UserNameAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(NewAdminCreationRestricted.class)
    public ResponseEntity<BaseResponseDto> newAdminCreationRestricted(NewAdminCreationRestricted e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(DuplicateCourseByInstructor.class)
    public ResponseEntity<BaseResponseDto> duplicateCourse(DuplicateCourseByInstructor e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(CourseNotFound.class)
    public ResponseEntity<BaseResponseDto> courseNotFound(CourseNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<BaseResponseDto> illegalState(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(LectureNotFound.class)
    public ResponseEntity<BaseResponseDto> lectureNotFound(LectureNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(FlixifyVideoDeleteException.class)
    public ResponseEntity<BaseResponseDto> flixifyVideoDelete(FlixifyVideoDeleteException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(FeatureNotImplementedYet.class)
    public ResponseEntity<BaseResponseDto> featureNotSupported(FeatureNotImplementedYet e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(ChapterAlreadyExist.class)
    public ResponseEntity<BaseResponseDto> chapterAlreadyExist(ChapterAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<BaseResponseDto> commentNotFound(CommentNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(LectureDiscussionNotFound.class)
    public ResponseEntity<BaseResponseDto> lectureDiscussionNotFound(LectureDiscussionNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(ReplyNotFound.class)
    public ResponseEntity<BaseResponseDto> replyNotFound(ReplyNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(LearnerNotFound.class)
    public ResponseEntity<BaseResponseDto> learnerNotFound(LearnerNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(LearnerAlreadyFollowing.class)
    public ResponseEntity<BaseResponseDto> learnerAlreadyFollowing(LearnerAlreadyFollowing e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(LearnerNotFollowingYet.class)
    public ResponseEntity<BaseResponseDto> learnerNotFollowingYet(LearnerNotFollowingYet e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(CourseAlreadyEnrolled.class)
    public ResponseEntity<BaseResponseDto> courseAlreadyEnrolled(CourseAlreadyEnrolled e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<BaseResponseDto> categoryNotFound(CategoryNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponseDto.builder().message(e.getMessage()).status(ResponseStatus.FAILURE).build()
        );
    }
}
