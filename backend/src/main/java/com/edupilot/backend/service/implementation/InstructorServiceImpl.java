package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.InstructorNotFound;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.InstructorRepository;
import com.edupilot.backend.service.interfaces.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    /**
     * Get the instructor based on id
     *
     * @param instructorId
     * @return
     */
    @Override
    public Instructor findInstructorById(Long instructorId) {

        return instructorRepository.findInstructorById(instructorId).orElseThrow(() -> new InstructorNotFound(instructorId, "id"));
    }

    /**
     * Find instructor by user
     *
     * @param user
     * @return
     */
    @Override
    public Instructor findInstructorByUser(User user) {

        return instructorRepository.findInstructorByUser(user).orElseThrow(() -> new InstructorNotFound(user.getId(), "user id"));
    }

    /**
     * @param instructor
     * @return
     */
    @Override
    public Instructor save(Instructor instructor) {

        return instructorRepository.save(instructor);
    }
}
