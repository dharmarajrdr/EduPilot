package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.User;

public interface InstructorService {

    public Instructor findInstructorById(Long id);

    public Instructor findInstructorByUser(User user);

    public Instructor save(Instructor instructor);
}
