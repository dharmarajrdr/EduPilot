package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.enums.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDto {

    private String message;

    private ResponseStatus status;

    private Object data;

    private Object info;
}
