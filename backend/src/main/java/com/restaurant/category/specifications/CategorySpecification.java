package com.restaurant.category.specifications;

import com.restaurant.category.entities.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;


public class CategorySpecification {

    public static Specification<CategoryEntity> findCategoryEntities(
            Integer id,
            String name

    ){


        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (id != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), id));
            }

            if (name != null) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            }

            return predicate;
        };

    }
}
