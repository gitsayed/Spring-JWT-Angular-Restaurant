package com.restaurant.food.service.impl;


import com.restaurant.food.domains.FoodDomain;
import com.restaurant.food.entities.FoodEntity;
import com.restaurant.food.repositories.FoodRepository;
import com.restaurant.food.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

 private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public Page<FoodEntity> findFoodInfo(Pageable pageable, Integer id, String name, Long price) {
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
    public List<FoodEntity> getAllFoods() {
        return null;
    }

    @Override
    public FoodEntity findFoodInfoById(Integer id) {
        return null;
    }


}
