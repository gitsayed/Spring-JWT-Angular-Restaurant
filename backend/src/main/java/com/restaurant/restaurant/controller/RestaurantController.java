package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.domains.RestaurantDomain;
import com.restaurant.restaurant.entities.RestaurantEntity;
import com.restaurant.restaurant.exceptions.RestaurantException;
import com.restaurant.restaurant.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
  private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService ) {

        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<?> findFoodInfo(Pageable pageable,
                                          @RequestParam(required = false) Integer id,
                                          @RequestParam(required = false) Long userId,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) String address,
                                          @RequestParam(required = false) String location,
                                          @RequestParam(required = false) String mobileNo,
                                          @RequestParam(required = false) String ownerName,
                                          @RequestParam(required = false) String licenceNo,
                                          @RequestParam(required = false) Date openingDate
    ) {
        log.info("Fetching Restaurant info.");
        Page<RestaurantEntity> restaurantEntityPage = restaurantService.findRestaurantInfo(pageable, id, userId, name, address, location, mobileNo, ownerName, licenceNo, openingDate);
        return ResponseEntity.ok(restaurantEntityPage);
    }

    @PostMapping
    public ResponseEntity<?> saveFood(@Valid @RequestBody RestaurantDomain restaurantDomain) {
        log.info("Saving Restaurant.");
        restaurantDomain.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        restaurantService.saveRestaurant(restaurantDomain);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantInfoById(@PathVariable Integer id

    ) {
        log.info("Fetching Restaurant info.");
        Optional<RestaurantEntity> restaurantEntity = restaurantService.findRestaurantInfoById(id);
        return ResponseEntity.ok(restaurantEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Integer id, @Valid @RequestBody RestaurantDomain restaurantDomain) {
        log.info("Update Restaurant.");
        restaurantDomain.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        if (id == null || id == 0) {
            log.error("Restaurant Id can't be null or zero.");
            throw new RestaurantException("Restaurant Id can't be null or zero.");
        }
        restaurantDomain.setId(id);
        restaurantService.saveRestaurant(restaurantDomain);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRestaurant(@PathVariable Integer id) {


        log.info("Deleting  Restaurant.");
        if (id == null || id == 0) {
            log.error("Restaurant Id can't be null or zero.");
            throw new RestaurantException("Restaurant Id can't be null or zero.");
        }
        if (!restaurantService.existsById(id)) {
            log.error("Restaurant not found by Id: {}", id);
            throw new RestaurantException("Restaurant not found by Id: " + id);
        }
        restaurantService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
