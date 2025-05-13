package com.edupilot.backend.strategy;

import com.edupilot.backend.custom_exception.FeatureNotImplementedYet;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edupilot.backend.dto.request.VideoDeleteRequestDto;
import com.edupilot.backend.dto.response.VideoDeleteResponseDto;
import com.edupilot.backend.dto.response.VideoUploadResponseDto;
import com.edupilot.backend.service.interfaces.VideoService;

import lombok.AllArgsConstructor;

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

        throw new FeatureNotImplementedYet("Upload video to flixify service");
    }

    /**
     * Delete the video in flixify
     *
     * @param videoDeleteRequestDto
     * @return
     */
    @Override
    public VideoDeleteResponseDto delete(VideoDeleteRequestDto videoDeleteRequestDto) {

        throw new FeatureNotImplementedYet("Delete video from flixify service");
    }
}
