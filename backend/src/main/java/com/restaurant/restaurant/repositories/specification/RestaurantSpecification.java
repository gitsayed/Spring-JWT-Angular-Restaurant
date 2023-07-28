package com.restaurant.restaurant.repositories.specification;


import com.restaurant.restaurant.entities.RestaurantEntity;
import com.restaurant.user.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Date;


public class RestaurantSpecification {

    public static Specification<RestaurantEntity> findRestaurantEntities(
            Integer id,
            UserEntity user,
            String name,
            String address,
            String location,
            String mobileNo,
            String ownerName,
            String licenceNo,
            Date openingDate
            ){


        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (id != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), id));
            }

            if (name != null) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            }

            if (user != null) {
                predicate = cb.and(predicate, cb.equal(root.get("user"), user));
            }

            if (address != null) {
                predicate = cb.and(predicate, cb.equal(root.get("address"), address));
            }

            if (location != null) {
                predicate = cb.and(predicate, cb.equal(root.get("location"), location));
            }

            if (mobileNo != null) {
                predicate = cb.and(predicate, cb.equal(root.get("mobileNo"), mobileNo));
            }

            if (ownerName != null) {
                predicate = cb.and(predicate, cb.equal(root.get("ownerName"), ownerName));
            }

            if (licenceNo != null) {
                predicate = cb.and(predicate, cb.equal(root.get("licenceNo"), licenceNo));
            }

            if (openingDate != null) {
                predicate = cb.and(predicate, cb.equal(root.get("openingDate"), openingDate));
            }

            return predicate;
        };

    }
}
