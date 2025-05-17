package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.dto.request.NotificationDto;
import com.edupilot.backend.dto.request.RejectInstructorRequestDto;
import com.edupilot.backend.model.Admin;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.repository.AdminRepository;
import com.edupilot.backend.service.interfaces.AdminService;
import com.edupilot.backend.service.interfaces.InstructorService;
import com.edupilot.backend.service.interfaces.NotificationService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final InstructorService instructorService;
    private final NotificationService notificationService;
    private final UserService userService;

    /**
     * Get an admin
     *
     * @return Admin
     */
    @Override
    public Admin getAdmin() {

        return adminRepository.findAll().getFirst();
    }

    /**
     * Approve Instructor to upload courses
     *
     * @param instructorId
     * @param adminId
     */
    @Override
    public void approveInstructor(Long instructorId, Long adminId) {

        Admin admin = adminRepository.findAdminByUser(userService.findUserById(adminId)).orElseThrow(() -> new PermissionDenied("Permission denied. Only admins can approve instructors."));
        Instructor instructor = instructorService.findInstructorByUser(userService.findUserById(instructorId));

        instructorService.verified(instructor);

        NotificationDto notificationDto = NotificationDto.builder()
                .fromUser(admin.getUser())
                .toUser(instructor.getUser())
                .subject("Role 'Instructor' Approved.")
                .message("Your account has been approved for the role 'Instructor'. You can now upload courses on EduPilot platform.")
                .build();
        notificationService.notifyAsync(notificationDto);
    }

    /**
     * Reject the instructor
     *
     * @param instructorId
     * @param adminId
     * @param rejectInstructorRequestDto
     */
    @Override
    public void rejectInstructor(Long instructorId, Long adminId, RejectInstructorRequestDto rejectInstructorRequestDto) {

        Admin admin = adminRepository.findAdminByUser(userService.findUserById(adminId)).orElseThrow(() -> new PermissionDenied("Permission denied. Only admins can reject instructors."));
        Instructor instructor = instructorService.findInstructorByUser(userService.findUserById(instructorId));

        instructorService.delete(instructor);

        NotificationDto notificationDto = NotificationDto.builder()
                .fromUser(admin.getUser())
                .toUser(instructor.getUser())
                .subject("Role 'Instructor' rejected.")
                .message("Your account has been rejected for the role 'Instructor'. Reason: " + rejectInstructorRequestDto.getReason())
                .build();
        notificationService.notifyAsync(notificationDto);
    }

    /**
     * Add an admin
     *
     * @param admin
     */
    @Override
    public void save(Admin admin) {

        adminRepository.save(admin);
    }

}
