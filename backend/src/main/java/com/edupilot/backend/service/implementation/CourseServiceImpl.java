package com.edupilot.backend.service.implementation;

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

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TagService tagsService;

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
        Instructor instructor = instructorService.findInstructorByUserId(userId);
        Category category = course.getCategory() == null ? null : categoryService.save(course.getCategory());
        List<Tag> tags = course.getTags() == null ? null : tagsService.saveAll(course.getTags());
        course.setTags(tags);
        course.setCourseStatus(CourseStatus.DRAFT);
        course.setCategory(category);
        course.setInstructor(instructor);
        courseRepository.save(course);
        return CreateCourseResponseDto.fromCourse(course);
    }
}
