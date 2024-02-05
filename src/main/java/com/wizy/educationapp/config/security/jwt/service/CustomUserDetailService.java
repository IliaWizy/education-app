package com.wizy.educationapp.config.security.jwt.service;

import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserService userService;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return userService.getByEmail(username);
    } catch (com.wizy.educationapp.service.exception.UsernameNotFoundException ex) {
      throw new UsernameNotFoundException(ex.getMessage(), ex);
    }
  }
}
