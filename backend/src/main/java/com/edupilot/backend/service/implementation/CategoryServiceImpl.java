package com.edupilot.backend.service.implementation;

import com.edupilot.backend.model.Category;
import com.edupilot.backend.repository.CategoryRepository;
import com.edupilot.backend.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * @param category
     * @return
     */
    @Override
    public Category save(Category category) {

        String name = category.getName();
        return existCategoryByName(name) ? categoryRepository.findCategoryByName(name) : categoryRepository.save(category);
    }

    /**
     * @param categoryName
     * @return
     */
    @Override
    public Category findCategoryByName(String categoryName) {
        return null;
    }

    /**
     * @param categoryName
     * @return
     */
    @Override
    public Boolean existCategoryByName(String categoryName) {

        return categoryRepository.existsByName(categoryName);
    }
}
