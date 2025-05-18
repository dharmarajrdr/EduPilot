package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CourseAlreadyEnrolled;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.response.CreatePaymentLinkResponseDto;
import com.edupilot.backend.factory.EnrollCourseFactory;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.LearnerCourse;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.repository.LearnerCourseRepository;
import com.edupilot.backend.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LearnerCourseServiceImpl implements LearnerCourseService {

    private final CourseService courseService;
    private final EnrollCourseFactory enrollCourseFactory;
    private final UserService userService;
    private final LearnerCourseRepository learnerCourseRepository;
    private final LearnerService learnerService;

    /**
     * Enroll in a  course
     *
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public CreatePaymentLinkResponseDto enrollCourse(Long courseId, Long userId) {

        User user = userService.findUserById(userId);
        if (!user.getUserType().equals(UserType.LEARNER)) {
            throw new PermissionDenied("Only learners can enroll courses");
        }

        Course course = courseService.getCourseById(courseId);
        Learner learner = learnerService.findLearnerByUser(user);

        boolean isAlreadyEnrolled = learnerCourseRepository.existsByCourseAndLearner(course, learner);

        if (isAlreadyEnrolled) {
            throw new CourseAlreadyEnrolled();
        }

        EnrollCourseService enrollCourseService = enrollCourseFactory.getEnrollCourseService(course.getSubscriptionType());
        return enrollCourseService.enroll(course, learner);
    }

    /**
     * Post payment of course purchase
     *
     * @param courseId
     * @param userId
     */
    @Override
    public void purchaseCourse(Long courseId, Long userId) {

        User user = userService.findUserById(userId);
        if (!user.getUserType().equals(UserType.LEARNER)) {
            throw new PermissionDenied("Only learners can purchase courses");
        }

        Course course = courseService.getCourseById(courseId);
        Learner learner = learnerService.findLearnerByUser(user);

        learnerCourseRepository.save(new LearnerCourse(learner, course));
    }
}
