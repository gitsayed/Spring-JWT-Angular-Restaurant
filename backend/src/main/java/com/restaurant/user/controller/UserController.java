package com.restaurant.user.controller;


import com.restaurant.role.exceptions.RoleException;
import com.restaurant.user.domains.UserDomain;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.user.exceptions.UsersException;
import com.restaurant.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public  ResponseEntity<?> findUserInfo(Pageable pageable,
                                           @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
                                   @RequestParam(required = false) String email,
                                           @RequestParam(required = false) UserStatusEnum userStatus
    ) {
        log.info("Fetching user info.");
        Page<UserEntity> userEntityPage = userService.findUserEntities( pageable,id, username, email, userStatus);

        return ResponseEntity.ok(userEntityPage);
    }


    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDomain userDomain){
        userService.saveUser(userDomain);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDomain userDomain){
        log.info("Update User.");
        if (id == null || id == 0) {
            log.error("User Id can't be null or zero.");
            throw new UsersException("User Id can't be null or zero.");
        }
        userDomain.setId(id);
        userService.saveUser(userDomain);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id) {
        log.info("Delete User.");
        if (id == null || id == 0) {
            log.error("User Id can't be null or zero.");
            throw new RoleException("User Id can't be null or zero.");
        }
        if(!userService.existsById(id)){
            log.error("User not found by Id: {}", id);
            throw new RoleException("User not found by Id: "+ id);
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
