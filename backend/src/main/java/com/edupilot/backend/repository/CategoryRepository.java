package com.edupilot.backend.repository;

import com.edupilot.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Boolean existsByName(String name);

    Category findCategoryByName(String name);

    Optional<Category> findCategoryById(Long id);
}
