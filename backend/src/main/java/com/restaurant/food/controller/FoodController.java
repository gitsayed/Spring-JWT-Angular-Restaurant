package com.restaurant.food.controller;


import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.category.exceptions.CategoryException;
import com.restaurant.food.domains.FoodDomain;
import com.restaurant.food.entities.FoodEntity;
import com.restaurant.food.exceptions.FoodException;
import com.restaurant.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<?> findFoodInfo(Pageable pageable,
                                              @RequestParam(required = false) Integer id,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) Long price,
                                              @RequestParam(required = false) String restaurantName
    ) {
        log.info("Fetching Food info.");
        Page<FoodEntity> foodEntityPage = foodService.findFoodInfo( pageable,id, name, price);
        return ResponseEntity.ok(foodEntityPage);
    }

    @PostMapping
    public ResponseEntity<?> saveFood(@Valid @RequestBody FoodDomain foodDomain){
        log.info("Saving Food.");
        foodService.saveFood(foodDomain);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFoodInfoById(@PathVariable Integer id

    ) {
        log.info("Fetching Food info.");
        FoodEntity foodEntity = foodService.findFoodInfoById(id);
        return ResponseEntity.ok(foodEntity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Integer id, @Valid @RequestBody FoodDomain foodDomain){
        log.info("Update Food.");
        if (id == null || id == 0) {
            log.error("Food Id can't be null or zero.");
            throw new FoodException("Food Id can't be null or zero.");
        }
        foodDomain.setId(id);
        foodService.saveFood(foodDomain);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFood(@PathVariable Integer id) {
        log.info("Deleting Food.");
        if (id == null || id == 0) {
            log.error("Food Id can't be null or zero.");
            throw new FoodException("Food Id can't be null or zero.");
        }
        if(!foodService.existsById(id)){
            log.error("Food not found by Id: {}", id);
            throw new FoodException("Food not found by Id: "+ id);
        }
        foodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
