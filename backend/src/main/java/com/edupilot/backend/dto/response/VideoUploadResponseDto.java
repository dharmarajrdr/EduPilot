package com.edupilot.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class VideoUploadResponseDto {

    private UUID videoId;
}
