package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.repository.CourseRepository;
import com.edupilot.backend.service.interfaces.CourseService;
import com.edupilot.backend.service.interfaces.InstructorService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final UserService userService;

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
        course.setInstructor(instructor);
        courseRepository.save(course);
        return CreateCourseResponseDto.fromCourse(course);
    }
}
