package com.restaurant.user.service.impl;

import com.restaurant.jwts.repository.RoleRepository;
import com.restaurant.role.entities.RoleEntity;
import com.restaurant.role.exceptions.RoleException;
import com.restaurant.user.domains.UserDomain;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.user.repositories.UserRepository;
import com.restaurant.user.service.UserService;
import com.restaurant.user.specification.UserSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {

        return this.userRepository.findByUsernameAndAndUserStatus(username, UserStatusEnum.ACTIVE);

    }

    @Override
    public Page<UserEntity> findUserEntities(Pageable pageable, Long id, String username, String email, UserStatusEnum userStatus) {
        return userRepository.findAll(UserSpecification.findUserEntities(id, username, email, userStatus), pageable);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(UserDomain userDomain) {

        UserEntity entity = new UserEntity();
        if (userDomain.getId() != null && userDomain.getId() > 0l) {
            entity.setId(userDomain.getId());
        }
        entity.setUsername(userDomain.getUsername());
        entity.setEmail(userDomain.getEmail());
        entity.setUserStatus(userDomain.getUserStatus());
        if (userDomain.getRoles() != null && userDomain.getRoles().size() > 0) {
            Set<RoleEntity> roleEntities = new HashSet<>();
            userDomain.getRoles().forEach(role -> {
                RoleEntity userRoleEntity = roleRepository.findByName(role)
                        .orElseThrow(() -> new RoleException("Role is not found by name : " + role));
                roleEntities.add(userRoleEntity);
            });
            log.info("Adding Roles to user.");
            if(roleEntities!=null && roleEntities.size()>0){
                entity.setRoleEntities(roleEntities);
            }
        }

        if(userDomain.getPassword()!=null && userDomain.getPassword().equalsIgnoreCase("")){
            entity.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        }

        log.info("Saving User.");
        this.userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
