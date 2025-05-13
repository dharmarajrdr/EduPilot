package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.CreateLectureRequestDto;
import com.edupilot.backend.dto.response.CreateLectureResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface LectureService {

    public CreateLectureResponseDto addLecture(MultipartFile video, CreateLectureRequestDto createLectureRequestDto, Long userId);
}
