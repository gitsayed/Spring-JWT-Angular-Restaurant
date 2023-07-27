package com.restaurant.jwts.payload.request;

import com.restaurant.role.domains.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<ERole> roles;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


}
