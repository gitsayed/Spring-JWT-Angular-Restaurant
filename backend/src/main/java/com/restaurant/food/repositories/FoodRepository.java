package com.restaurant.food.repositories;

import com.restaurant.food.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface FoodRepository extends JpaRepository<FoodEntity, Integer>, JpaSpecificationExecutor {




}
