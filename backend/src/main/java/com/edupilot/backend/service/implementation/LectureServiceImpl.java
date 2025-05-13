package com.edupilot.backend.service.implementation;

import com.edupilot.backend.dto.request.CreateLectureRequestDto;
import com.edupilot.backend.dto.response.CreateLectureResponseDto;
import com.edupilot.backend.dto.response.VideoUploadResponseDto;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Lecture;
import com.edupilot.backend.repository.LectureRepository;
import com.edupilot.backend.service.interfaces.CourseService;
import com.edupilot.backend.service.interfaces.LectureService;
import com.edupilot.backend.service.interfaces.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final CourseService courseService;
    private final LectureRepository lectureRepository;
    private final VideoService videoService;

    /**
     * Creates a new lecture
     *
     * @param createLectureRequestDto
     * @return
     */
    @Override
    @Transactional
    public CreateLectureResponseDto addLecture(MultipartFile file, CreateLectureRequestDto createLectureRequestDto, Long userId) {

        Lecture lecture = createLectureRequestDto.toLecture();
        Long courseId = createLectureRequestDto.getCourseId();
        Course course = courseService.getCourseById(courseId);
        lecture.setCourse(course);

        VideoUploadResponseDto videoUploadResponseDto = videoService.upload(file);
        lecture.setVideoId(videoUploadResponseDto.getVideoId());

        return CreateLectureResponseDto.fromLecture(lectureRepository.save(lecture));
    }
}
