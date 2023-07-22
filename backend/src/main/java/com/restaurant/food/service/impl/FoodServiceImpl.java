package com.restaurant.food.service.impl;

import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.food.domains.FoodDomain;
import com.restaurant.food.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {


    @Override
    public Page<CategoryEntity> findFoodInfo(Pageable pageable, Integer id, String name) {
        return null;
    }

    @Override
    public void saveFood(FoodDomain foodDomain) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        return null;
    }
}
