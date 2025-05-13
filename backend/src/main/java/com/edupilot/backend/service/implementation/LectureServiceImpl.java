package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.ChapterAlreadyExist;
import com.edupilot.backend.custom_exception.FlixifyVideoDeleteException;
import com.edupilot.backend.custom_exception.LectureNotFound;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.CreateLectureRequestDto;
import com.edupilot.backend.dto.request.DeleteLectureRequestDto;
import com.edupilot.backend.dto.request.VideoDeleteRequestDto;
import com.edupilot.backend.dto.response.CreateLectureResponseDto;
import com.edupilot.backend.dto.response.VideoDeleteResponseDto;
import com.edupilot.backend.dto.response.VideoUploadResponseDto;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Lecture;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.LectureRepository;
import com.edupilot.backend.service.interfaces.CourseService;
import com.edupilot.backend.service.interfaces.LectureService;
import com.edupilot.backend.service.interfaces.UserService;
import com.edupilot.backend.service.interfaces.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final CourseService courseService;
    private final LectureRepository lectureRepository;
    private final VideoService videoService;
    private final UserService userService;

    /**
     * Check whether any chapter exist with same id
     *
     * @param chapterId
     * @param course
     */
    private void validateChapterExistence(Integer chapterId, Course course) {

        if (lectureRepository.existsLectureByChapterIdAndCourse(chapterId, course)) {
            throw new ChapterAlreadyExist(chapterId, course);
        }
    }

    /**
     * Creates a new lecture
     *
     * @param createLectureRequestDto
     * @return
     */
    @Override
    @Transactional
    public CreateLectureResponseDto addLecture(MultipartFile file, CreateLectureRequestDto createLectureRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        Lecture lecture = createLectureRequestDto.toLecture();
        Long courseId = createLectureRequestDto.getCourseId();
        Course course = courseService.getCourseById(courseId);
        lecture.setCourse(course);

        if (!course.isOwner(user)) {
            throw new PermissionDenied("Permission denied. You are not allowed to add lectures in this course.");
        }

        validateChapterExistence(createLectureRequestDto.getChapterId(), course);

        VideoUploadResponseDto videoUploadResponseDto = videoService.upload(file);
        lecture.setVideoId(videoUploadResponseDto.getVideoId());

        return CreateLectureResponseDto.fromLecture(lectureRepository.save(lecture));
    }

    /**
     * Find the lecture in given course
     *
     * @param lectureId
     * @param courseId
     * @return
     */
    @Override
    public Lecture findLectureByIdAndCourseId(Long lectureId, Long courseId) {

        Course course = courseService.getCourseById(courseId);
        return lectureRepository.findLectureByIdAndCourse(lectureId, course).orElseThrow(() -> new LectureNotFound(lectureId, course.getId()));
    }

    /**
     * Delete the given lecture in the course
     *
     * @param deleteLectureRequestDto
     * @param userId
     */
    @Override
    public void deleteLecture(DeleteLectureRequestDto deleteLectureRequestDto, Long userId) {

        Lecture lecture = findLectureByIdAndCourseId(deleteLectureRequestDto.getLectureId(), deleteLectureRequestDto.getCourseId());
        UUID videoId = lecture.getVideoId();
        VideoDeleteRequestDto videoDeleteRequestDto = VideoDeleteRequestDto.builder().videoId(videoId).build();

        VideoDeleteResponseDto videoDeleteResponseDto = videoService.delete(videoDeleteRequestDto);
        if (videoDeleteResponseDto.getStatus().isFailure()) {
            throw new FlixifyVideoDeleteException(videoDeleteResponseDto.getMessage());
        } else {
            lectureRepository.delete(lecture);
        }
    }
}
