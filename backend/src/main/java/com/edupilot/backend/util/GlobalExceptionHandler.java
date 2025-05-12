package com.edupilot.backend.util;

import com.edupilot.backend.custom_exception.AccountAlreadyRegistered;
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
}
