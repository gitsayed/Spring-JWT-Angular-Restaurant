package com.restaurant.user.service;

import com.restaurant.user.domains.UserDomain;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findUserEntities(Pageable pageable, Long id, String username, String email, UserStatusEnum userStatus);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void saveUser(UserDomain userDomain);
    List<UserEntity> getUsers( );
    Boolean existsById(Long id);
    void deleteById(Long id);
}
