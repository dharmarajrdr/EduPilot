package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.response.VideoUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    public VideoUploadResponseDto upload(MultipartFile file);
}
