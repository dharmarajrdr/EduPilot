package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CourseNotFound;
import com.edupilot.backend.custom_exception.DuplicateCourseByInstructor;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.*;
import com.edupilot.backend.model.enums.CourseStatus;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.repository.CourseRepository;
import com.edupilot.backend.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final InstructorService instructorService;
    private final TagService tagsService;
    private final UserService userService;

    private Category saveCategory(Category category) {
        if (category == null) {
            return null;
        }
        return categoryService.save(category);
    }

    private List<Tag> saveTags(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        return tagsService.saveAll(tags);
    }

    /**
     * Create a new course
     *
     * @param createCourseRequestDto
     * @return
     */
    @Override
    public CreateCourseResponseDto createCourse(CreateCourseRequestDto createCourseRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        boolean isInstructor = user.getUserType().equals(UserType.INSTRUCTOR);
        if (!isInstructor) {
            throw new PermissionDenied("You are not allowed to create a course. Only instructors can create course.");
        }

        Course course = createCourseRequestDto.toCourse();

        course.setInstructor(instructorService.findInstructorByUser(user));

        if (courseRepository.existsCourseByTitleAndInstructor(course.getTitle(), course.getInstructor())) {
            throw new DuplicateCourseByInstructor(course);
        }

        course.setCategory(saveCategory(course.getCategory())); // save before referring
        course.setTags(saveTags(course.getTags()));     // save before referring
        course.setCourseStatus(CourseStatus.DRAFT);

        courseRepository.save(course);
        return CreateCourseResponseDto.fromCourse(course);
    }

    /**
     * Update course status
     *
     * @param userId
     * @param courseId
     * @param courseStatus
     */
    private void updateCourseStatus(Long userId, Long courseId, CourseStatus courseStatus) {

        User user = userService.findUserById(userId);
        boolean isInstructor = user.getUserType().equals(UserType.INSTRUCTOR);

        if (!isInstructor) {
            throw new PermissionDenied("You are not allowed to edit a course. Only instructors can edit this course.");
        }

        Course course = getCourseById(courseId);
        if (!course.getInstructor().getUser().getId().equals(userId)) {
            throw new PermissionDenied("Permission denied! Only course owner can edit this course.");
        }

        if (course.getCourseStatus().equals(courseStatus)) {
            throw new IllegalStateException("The course status is already set to " + courseStatus);
        }

        if (courseStatus.equals(CourseStatus.PUBLISHED) && course.getReleasedDate() == null) {
            course.setReleasedDate(LocalDateTime.now());
        }

        course.setCourseStatus(courseStatus);
        courseRepository.save(course);
    }

    /**
     * Publish the course
     *
     * @param courseId
     * @param userId
     */
    @Override
    public void publishCourse(Long courseId, Long userId) {

        updateCourseStatus(userId, courseId, CourseStatus.PUBLISHED);
    }

    /**
     * Archive the course
     *
     * @param courseId
     * @param userId
     */
    @Override
    public void archiveCourse(Long courseId, Long userId) {

        updateCourseStatus(userId, courseId, CourseStatus.ARCHIVED);
    }

    /**
     * Get course by id
     *
     * @param courseId
     * @return
     */
    @Override
    public Course getCourseById(Long courseId) {

        return courseRepository.findCourseById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
    }
}
