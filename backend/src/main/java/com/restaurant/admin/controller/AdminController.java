package com.restaurant.admin.controller;


import com.restaurant.admin.domains.CustomerUserResponse;
import com.restaurant.admin.domains.UpdateUserRequest;
import com.restaurant.admin.service.AdminService;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.exceptions.UsersException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user-status")
    public ResponseEntity<?> fetchUserWithStatus(Pageable pageable,
                                                 @RequestParam("status") UserStatusEnum status
    ) {
        log.info("Fetching user Info with status : {}", status);
        Page<CustomerUserResponse> pagedUser = adminService.fetchUserWithStatus(pageable, status);
        return ResponseEntity.ok().body(pagedUser);
    }

    @PutMapping("/user-status/{id}")
    public ResponseEntity<?> updateUserWithStatus(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request ) {
        log.info("Updating user Info with status : {}", request.getStatus());
        if(id!=request.getId()) throw new UsersException("User ID mismatched!");
         adminService.updateUserWithStatus(request);
        return ResponseEntity.ok().build();
    }
}
