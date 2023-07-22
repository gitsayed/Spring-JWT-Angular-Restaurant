package com.restaurant.user.specification;

import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;


public class UserSpecification {

    public static Specification<UserEntity> findUserEntities(
            Long id,
            String username,
            String email,
            UserStatusEnum userStatus
    ){


        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (id != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), id));
            }

            if (username != null) {
                predicate = cb.and(predicate, cb.equal(root.get("username"), username));
            }

            if (email != null) {
                predicate = cb.and(predicate, cb.equal(root.get("email"), email));
            }

            if (userStatus != null) {
                predicate = cb.and(predicate, cb.equal(root.get("userStatus"), userStatus));
            }

            return predicate;
        };

    }
}
