package com.restaurant.admin.domains;


import com.restaurant.user.domains.UserStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {

    @NotNull
    private Long id;
    @NotNull
    private UserStatusEnum status;
}
