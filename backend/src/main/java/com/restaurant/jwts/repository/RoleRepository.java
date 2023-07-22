package com.restaurant.jwts.repository;

import com.restaurant.role.domains.ERole;
import com.restaurant.role.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
  Optional<RoleEntity> findByName(ERole name);
  boolean existsByName(ERole name);
}
