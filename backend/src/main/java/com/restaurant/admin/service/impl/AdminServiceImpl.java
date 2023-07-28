package com.restaurant.admin.service.impl;

import com.restaurant.admin.domains.CustomerUserResponse;
import com.restaurant.admin.domains.UpdateUserRequest;
import com.restaurant.admin.service.AdminService;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.user.repositories.UserRepository;
import com.restaurant.user.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<CustomerUserResponse> fetchUserWithStatus(Pageable pageable, UserStatusEnum status) {

        Page<UserEntity> pagedUsers = userRepository.findAll(UserSpecification.findUserEntities(null, null, null, status), pageable);

        List<CustomerUserResponse> userResList = new ArrayList<>();
        List<UserEntity> userEntityList = pagedUsers.getContent();
        if (!userEntityList.isEmpty()) {
            userEntityList.forEach(entity -> {
                userResList.add(new CustomerUserResponse()
                        .setUsername(entity.getUsername())
                        .setEmail(entity.getEmail())
                        .setId(entity.getId())
                        .setStatus(entity.getUserStatus()));
            });
        }
        return  new PageImpl(userResList, pagedUsers.getPageable(), pagedUsers.getTotalElements());


    }

    @Override
    public void updateUserWithStatus(UpdateUserRequest request) {

        Optional<UserEntity> opEntity = userRepository.findById(request.getId());
        opEntity.ifPresent(entity->{
            entity.setUserStatus(request.getStatus());
            userRepository.save(entity);
        });
    }


}
