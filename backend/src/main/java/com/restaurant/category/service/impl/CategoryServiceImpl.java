package com.restaurant.category.service.impl;

import com.restaurant.category.domains.CategoryDomain;
import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.category.repositories.CategoryRepository;
import com.restaurant.category.service.CategoryService;
import com.restaurant.category.specifications.CategorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryEntity> findCategoryInfo(Pageable pageable, Integer id, String name) {
        return categoryRepository.findAll(CategorySpecification.findCategoryEntities(id, name), pageable);
    }

    @Override
    public void saveCategory(CategoryDomain categoryDomain) {
        CategoryEntity entity = new CategoryEntity();
        if (categoryDomain.getId() != null && categoryDomain.getId() > 0l) {
            entity.setId(categoryDomain.getId());
        }
        entity.setName(categoryDomain.getName());
        categoryRepository.save(entity);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return  categoryRepository.existsById(id);
    }

    @Override
    public List<CategoryEntity> getAllCategory() {

        return categoryRepository.findAll();
    }
}
