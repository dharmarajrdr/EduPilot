package com.edupilot.backend.strategy;

import com.edupilot.backend.dto.response.VideoUploadResponseDto;
import com.edupilot.backend.service.interfaces.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Primary
@AllArgsConstructor
public class FlixifyVideoService implements VideoService {

    /**
     * Upload the given video in `flixify` service
     * https://github.com/dharmarajrdr/Flixify/blob/master/REST_API.md#upload-video
     *
     * @param file
     */
    @Override
    public VideoUploadResponseDto upload(MultipartFile file) {

        return null;
    }
}
