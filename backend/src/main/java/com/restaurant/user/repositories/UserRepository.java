package com.restaurant.user.repositories;

import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor {
  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUsernameAndUserStatus(String username, UserStatusEnum userStatusEnum);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
