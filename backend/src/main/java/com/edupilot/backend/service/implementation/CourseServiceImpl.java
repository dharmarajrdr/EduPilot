package com.edupilot.backend.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import com.edupilot.backend.service.interfaces.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edupilot.backend.custom_exception.CourseNotFound;
import com.edupilot.backend.custom_exception.DuplicateCourseByInstructor;
import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.CreateCourseRequestDto;
import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.dto.response.CreateCourseResponseDto;
import com.edupilot.backend.model.Admin;
import com.edupilot.backend.model.Category;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.Tag;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.CourseStatus;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final AdminService adminService;
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final InstructorService instructorService;
    private final NotificationService notificationService;
    private final TagService tagsService;
    private final UserService userService;
    private final CourseStripeService courseStripeService;

    public CourseServiceImpl(@Qualifier("emailNotificationService") NotificationService notificationService, UserService userService, TagService tagsService, AdminService adminService, CourseRepository courseRepository, CategoryService categoryService, InstructorService instructorService, CourseStripeService courseStripeService) {
        this.userService = userService;
        this.tagsService = tagsService;
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
        this.notificationService = notificationService;
        this.courseStripeService = courseStripeService;
    }

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

        Instructor instructor = instructorService.findInstructorByUser(user);
        course.setInstructor(instructor);

        if (!instructor.isVerified()) {
            throw new PermissionDenied("Your profile is not verified yet. Please contact your administrator.");
        }

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
     * @return Course
     */
    private Course updateCourseStatus(Long userId, Long courseId, CourseStatus courseStatus) {

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
        return courseRepository.save(course);
    }

    /**
     * Notify about course publication to learners
     *
     * @param course
     */
    private void notifyCoursePublication(Course course) {

        Admin admin = adminService.getAdmin();
        if (admin == null) {
            return;
        }
        User adminUser = admin.getUser();
        String subject = "New Course for you!";
        Instructor instructor = course.getInstructor();
        List<Learner> followers = instructor.getFollowers();
        for (Learner learner : followers) {
            String message = "Hey " + learner.getUser().getDisplayName() + "! Your favourite instructor '" + instructor.getUser().getDisplayName() + "' has published a course '" + course.getTitle() + "'.";
            NotificationDto notificationDto = NotificationDto.builder().fromUser(adminUser).toUser(learner.getUser()).message(message).subject(subject).build();
            notificationService.notifyAsync(notificationDto);
        }
    }

    /**
     * Publish the course
     *
     * @param courseId
     * @param userId
     */
    @Override
    public void publishCourse(Long courseId, Long userId) throws Exception {

        boolean isNewlyPublishing = getCourseById(courseId).getReleasedDate() == null;

        Course course = updateCourseStatus(userId, courseId, CourseStatus.PUBLISHED);

        if (isNewlyPublishing) {

            courseStripeService.save(course);
            notifyCoursePublication(course);
        }
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
