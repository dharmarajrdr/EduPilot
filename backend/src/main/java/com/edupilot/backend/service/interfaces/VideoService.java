package com.edupilot.backend.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.edupilot.backend.dto.request.VideoDeleteRequestDto;
import com.edupilot.backend.dto.response.VideoDeleteResponseDto;
import com.edupilot.backend.dto.response.VideoUploadResponseDto;

public interface VideoService {

    public VideoUploadResponseDto upload(MultipartFile file);

    public VideoDeleteResponseDto delete(VideoDeleteRequestDto videoDeleteRequestDto);
}
