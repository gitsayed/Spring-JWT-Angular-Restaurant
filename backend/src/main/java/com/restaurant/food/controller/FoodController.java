package com.restaurant.food.controller;


import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.category.exceptions.CategoryException;
import com.restaurant.food.domains.FoodDomain;
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
    public ResponseEntity<?> findCategoryInfo(Pageable pageable,
                                              @RequestParam(required = false) Integer id,
                                              @RequestParam(required = false) String name
    ) {
        log.info("Fetching Food info.");
        Page<CategoryEntity> categoryEntityPage = foodService.findFoodInfo( pageable,id, name);
        return ResponseEntity.ok(categoryEntityPage);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@Valid @RequestBody FoodDomain foodDomain){
        log.info("Saving Food.");
        foodService.saveFood(foodDomain);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @RequestBody FoodDomain foodDomain){
        log.info("Update Food.");
        if (id == null || id == 0) {
            log.error("Food Id can't be null or zero.");
            throw new CategoryException("Food Id can't be null or zero.");
        }
        foodDomain.setId(id);
        foodService.saveFood(foodDomain);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
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
