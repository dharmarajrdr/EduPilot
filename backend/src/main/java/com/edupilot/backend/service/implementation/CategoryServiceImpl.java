package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.CategoryNotFound;
import com.edupilot.backend.model.Category;
import com.edupilot.backend.repository.CategoryRepository;
import com.edupilot.backend.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Save the new category
     *
     * @param category
     * @return
     */
    @Override
    @CachePut(value = "CATEGORY", key = "#result.id()")
    public Category save(Category category) {

        String name = category.getName();
        return existCategoryByName(name) ? categoryRepository.findCategoryByName(name) : categoryRepository.save(category);
    }

    /**
     * Get the category by id
     *
     * @param categoryId
     * @return
     */
    @Override
    @Cacheable(value = "CATEGORY", key = "#categoryId")
    public Category findCategoryById(Long categoryId) {

        return categoryRepository.findCategoryById(categoryId).orElseThrow(() -> new CategoryNotFound(categoryId));
    }

    /**
     * Find the category by name
     *
     * @param categoryName
     * @return
     */
    @Override
    public Category findCategoryByName(String categoryName) {

        return null;
    }

    /**
     * Check whether the category exist of not
     *
     * @param categoryName
     * @return
     */
    @Override
    public Boolean existCategoryByName(String categoryName) {

        return categoryRepository.existsByName(categoryName);
    }

    /**
     * Delete category based on id
     *
     * @param categoryId
     */
    @Override
    @CacheEvict(value = "CATEGORY", key = "#categoryId")
    public void deleteCategoryById(Long categoryId) {

        categoryRepository.deleteById(categoryId);
    }
}
