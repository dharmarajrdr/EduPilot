package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.dto.request.RejectInstructorRequestDto;
import com.edupilot.backend.model.Admin;

public interface AdminService {

    public Admin getAdmin();

    public void approveInstructor(Long instructorId, Long adminId);

    public void rejectInstructor(Long instructorId, Long adminId, RejectInstructorRequestDto rejectInstructorRequestDto);

    public void save(Admin admin);
}
