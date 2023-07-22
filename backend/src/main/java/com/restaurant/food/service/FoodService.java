package com.restaurant.food.service;

import com.restaurant.category.domains.CategoryDomain;
import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.food.domains.FoodDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    Page<CategoryEntity> findFoodInfo(Pageable pageable, Integer id, String name);

    void saveFood(FoodDomain foodDomain);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<CategoryEntity> getAllCategory();
}
