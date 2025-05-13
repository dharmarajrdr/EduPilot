package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoDeleteResponseDto {

    private ResponseStatus status;

    private String message;
}
