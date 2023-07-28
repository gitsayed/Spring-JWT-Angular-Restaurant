package com.restaurant.admin.domains;

import com.restaurant.user.domains.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerUserResponse {

    private Long id;
    private String username;
    private String email;
    private UserStatusEnum status;

}
