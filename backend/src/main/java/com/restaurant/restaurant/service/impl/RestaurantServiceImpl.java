package com.restaurant.restaurant.service.impl;

import com.restaurant.restaurant.domains.RestaurantDomain;
import com.restaurant.restaurant.entities.RestaurantEntity;
import com.restaurant.restaurant.exceptions.RestaurantException;
import com.restaurant.restaurant.repositories.RestaurantRepository;
import com.restaurant.restaurant.repositories.specification.RestaurantSpecification;
import com.restaurant.restaurant.service.RestaurantService;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.user.exceptions.UsersException;
import com.restaurant.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<RestaurantEntity> findRestaurantInfo(Pageable pageable, Integer id, Long userId, String name, String address, String location, String mobileNo, String ownerName, String licenceNo, Date openingDate) {
        UserEntity user = null;
        if (userId != null) {
            user = userRepository.findById(userId).orElseThrow(() -> new UsersException("User not found by Id: " + userId));
        }
        return restaurantRepository.findAll(RestaurantSpecification.findRestaurantEntities(
                id, user, name, address, location, mobileNo, ownerName, licenceNo, openingDate),
                pageable);
    }

    @Override
    public void saveRestaurant(RestaurantDomain domain) {
        log.info("Saving Restaurant.");
        RestaurantEntity entity = new RestaurantEntity();
        if (domain.getId() != null) {
            entity = restaurantRepository.findById(domain.getId()).orElseThrow(() ->
                    new RestaurantException("Restaurant not found by Id: " + domain.getId()));
        }
        if (domain.getUserId() != null) {
            entity.setUser(userRepository.findById(domain.getUserId()).orElseThrow(() -> new UsersException("User not found by Id: " + domain.getUserId())));
        }

        entity.setName(domain.getName())
                .setAddress(domain.getAddress())
                .setLocation(domain.getLocation())
                .setOwnerName(domain.getOwnerName())
                .setMobileNo(domain.getMobileNo())
                .setLicenceNo(domain.getLicenceNo())
                .setOpeningDate(domain.getOpeningDate())
                .setCreatedBy(domain.getCreatedBy())
                .setUpdatedBy(domain.getUpdatedBy());
        
        restaurantRepository.save(entity);

    }

    @Override
    public Optional<RestaurantEntity> findRestaurantInfoById(Integer id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }
}
