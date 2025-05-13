package com.edupilot.backend.repository;

import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findInstructorById(Long instructorId);

    Optional<Instructor> findInstructorByUser(User user);
}
