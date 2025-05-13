package com.edupilot.backend.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VideoDeleteRequestDto {

    private UUID videoId;
}
