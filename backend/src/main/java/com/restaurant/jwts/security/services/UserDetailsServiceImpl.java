package com.restaurant.jwts.security.services;

import com.restaurant.user.domains.UserStatusEnum;
import com.restaurant.user.entities.UserEntity;
import com.restaurant.user.exceptions.UsersException;
import com.restaurant.user.repositories.UserRepository;
import com.restaurant.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByUsernameAndUserStatus(username, UserStatusEnum.ACTIVE)
        .orElseThrow(() -> new UsersException("User Not Found with username: " + username));

    return UserDetailsImpl.build(userEntity);
  }

}
