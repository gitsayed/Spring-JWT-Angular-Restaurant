package com.restaurant.food.repositories.specification;

import com.restaurant.food.entities.FoodEntity;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;


public class FoodSpecification {

    public static Specification<FoodEntity> findFoodEntities(
            Integer id,
            String name,
            Long price

    ){


        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (id != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), id));
            }

            if (name != null) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            }

            if (name != null) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            }

            return predicate;
        };

    }
}
