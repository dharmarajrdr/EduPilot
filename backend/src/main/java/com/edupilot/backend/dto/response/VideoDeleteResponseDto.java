package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.enums.ResponseStatus;
import lombok.Data;

@Data
public class VideoDeleteResponseDto {

    private ResponseStatus status;

    private String message;
}
