package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Category;

public interface CategoryService {

    Category save(Category category);

    Category findCategoryByName(String categoryName);

    Boolean existCategoryByName(String categoryName);
}
