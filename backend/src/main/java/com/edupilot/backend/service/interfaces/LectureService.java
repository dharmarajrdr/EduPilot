package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.CreateLectureRequestDto;
import com.edupilot.backend.dto.request.DeleteLectureRequestDto;
import com.edupilot.backend.dto.response.CreateLectureResponseDto;
import com.edupilot.backend.model.Lecture;
import org.springframework.web.multipart.MultipartFile;

public interface LectureService {

    public CreateLectureResponseDto addLecture(MultipartFile video, CreateLectureRequestDto createLectureRequestDto, Long userId);

    Lecture findLectureByIdAndCourseId(Long lectureId, Long courseId);

    public void deleteLecture(DeleteLectureRequestDto deleteLectureRequestDto, Long userId);
}
