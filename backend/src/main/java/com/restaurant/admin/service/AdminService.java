package com.restaurant.admin.service;

import com.restaurant.admin.domains.CustomerUserResponse;
import com.restaurant.admin.domains.UpdateUserRequest;
import com.restaurant.user.domains.UserStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {


    public Page<CustomerUserResponse> fetchUserWithStatus(Pageable pageable, UserStatusEnum status);

    void updateUserWithStatus(UpdateUserRequest request);
}
