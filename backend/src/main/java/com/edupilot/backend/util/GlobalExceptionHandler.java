package com.edupilot.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edupilot.backend.custom_exception.AccountAlreadyRegistered;
import com.edupilot.backend.custom_exception.ChapterAlreadyExist;
import com.edupilot.backend.custom_exception.CommentNotFound;
import com.edupilot.backend.custom_exception.CourseNotFound;
import com.edupilot.backend.custom_exception.DuplicateCourseByInstructor;
import com.edupilot.backend.custom_exception.FeatureNotImplementedYet;
import com.edupilot.backend.custom_exception.FlixifyVideoDeleteException;
import com.edupilot.backend.custom_exception.InstructorNotFound;
import com.edupilot.backend.custom_exception.LectureDiscussionNotFound;
import com.edupilot.backend.custom_exception.LectureNotFound;
import com.edupilot.backend.custom_exception.NewAdminCreationRestricted;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.custom_exception.ReplyNotFound;
import com.edupilot.backend.custom_exception.UserNameAlreadyExist;
import com.edupilot.backend.custom_exception.UserNotFound;
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
}
