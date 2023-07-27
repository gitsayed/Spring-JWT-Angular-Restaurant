package com.restaurant.restaurant.repositories;

import com.restaurant.restaurant.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>, JpaSpecificationExecutor {


}
