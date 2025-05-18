package com.edupilot.backend.repository;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Order;
import com.edupilot.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public boolean existsByCourseAndUser(Course course, User user);

    public Optional<Order> findByCourseAndUser(Course course, User user);
}
