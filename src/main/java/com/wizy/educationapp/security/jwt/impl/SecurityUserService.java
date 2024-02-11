package com.wizy.educationapp.security.jwt.impl;

import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.security.jwt.entity.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new SecurityUser(
            userRepository
            .findByEmail(email)
            .orElseThrow(
                    () -> new UsernameNotFoundException("User not found with email: " + email)
            ));
  }
}
