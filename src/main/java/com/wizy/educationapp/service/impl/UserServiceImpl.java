package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import com.wizy.educationapp.service.exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Override
  public void checkExists(String email) {
    userRepository.findByEmail(email).ifPresent(existingUser -> {
      throw new UserIsExistingException(
          "User already exists.", existingUser.getEmail());
    });
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(User user) {
    userRepository.delete(user);
  }


}
