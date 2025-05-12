package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Instructor;

public interface InstructorService {

    public Instructor findInstructorById(Long id);

    public Instructor findInstructorByUserId(Long userId);

    public Instructor save(Instructor instructor);
}
