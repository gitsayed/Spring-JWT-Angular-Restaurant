package com.restaurant.category.service;

import com.restaurant.category.domains.CategoryDomain;
import com.restaurant.category.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<CategoryEntity> findCategoryInfo(Pageable pageable, Integer id, String name);

    void saveCategory(CategoryDomain categoryDomain);

    void deleteCategoryById(Integer id);

    boolean existsById(Integer id);

    List<CategoryEntity> getAllCategory();
}
