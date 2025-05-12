package com.edupilot.backend.util;

import com.edupilot.backend.custom_exception.*;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
