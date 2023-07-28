package com.restaurant.restaurant.service;

import com.restaurant.food.entities.FoodEntity;
import com.restaurant.restaurant.domains.RestaurantDomain;
import com.restaurant.restaurant.entities.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface RestaurantService {
    Page<RestaurantEntity> findRestaurantInfo(Pageable pageable,
                                              Integer id,
                                              Long userId,
                                              String name,
                                              String address,
                                              String location,
                                              String mobileNo,
                                              String ownerName,
                                              String licenceNo,
                                              Date openingDate
    );


    void saveRestaurant(RestaurantDomain restaurantDomain);

    Optional<RestaurantEntity> findRestaurantInfoById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
