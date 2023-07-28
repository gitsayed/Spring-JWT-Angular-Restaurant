package com.restaurant.user.domains;

import com.restaurant.role.domains.ERole;
import lombok.Data;

import java.util.Set;


@Data
public class UserDomain {

    private Long id;
    private String username;
    private String password;
    private String email;
    private UserStatusEnum userStatus;
    private Set<ERole> roles;
}
