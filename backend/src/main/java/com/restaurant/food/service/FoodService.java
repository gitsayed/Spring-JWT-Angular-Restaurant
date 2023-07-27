package com.restaurant.food.service;

import com.restaurant.food.domains.FoodDomain;
import com.restaurant.food.entities.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    Page<FoodEntity> findFoodInfo(Pageable pageable, Integer id, String name, Long price);

    void saveFood(FoodDomain foodDomain);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<FoodEntity> getAllFoods();

    FoodEntity findFoodInfoById(Integer id);
}
