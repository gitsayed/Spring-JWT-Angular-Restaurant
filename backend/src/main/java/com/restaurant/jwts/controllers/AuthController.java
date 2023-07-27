package com.restaurant.jwts.controllers;


import com.restaurant.role.domains.ERole;
import com.restaurant.role.entities.RoleEntity;
import com.restaurant.role.exceptions.RoleException;
import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.jwts.payload.request.LoginRequest;
import com.restaurant.jwts.payload.request.SignupRequest;
import com.restaurant.jwts.payload.response.JwtResponse;
import com.restaurant.jwts.payload.response.MessageResponse;
import com.restaurant.jwts.repository.RoleRepository;
import com.restaurant.user.repositories.UserRepository;
import com.restaurant.jwts.security.jwt.JwtUtils;
import com.restaurant.jwts.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    UserEntity userEntity = new UserEntity(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<ERole> strRoles = signUpRequest.getRoles();
    Set<RoleEntity> roleEntities = new HashSet<>();

    if (strRoles == null) {
      RoleEntity userRoleEntity = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roleEntities.add(userRoleEntity);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case ROLE_ADMIN:
          RoleEntity adminRoleEntity = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RoleException("Error: Role is not found."));
          roleEntities.add(adminRoleEntity);

          break;
          case ROLE_RESTAURANT:
            RoleEntity restRoleEntity = roleRepository.findByName(ERole.ROLE_RESTAURANT)
                    .orElseThrow(() -> new RoleException("Error: Role is not found."));
            roleEntities.add(restRoleEntity);

            break;
          case ROLE_CUSTOMER:
            RoleEntity cusRoleEntity = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RoleException("Error: Role is not found."));
            roleEntities.add(cusRoleEntity);

            break;
        case ROLE_MODERATOR:
          RoleEntity modRoleEntity = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RoleException("Error: Role is not found."));
          roleEntities.add(modRoleEntity);

          break;
        default:
          RoleEntity userRoleEntity = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roleEntities.add(userRoleEntity);
        }
      });
    }

    userEntity.setRoleEntities(roleEntities);
    userEntity.setUserStatus(UserStatusEnum.IN_ACTIVE);
    userRepository.save(userEntity);

    return ResponseEntity.ok(new MessageResponse("User save successfully! Wait For Activation."));
  }
}
