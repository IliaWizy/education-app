package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.service.exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
