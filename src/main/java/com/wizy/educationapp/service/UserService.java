package com.wizy.educationapp.service;

import com.wizy.educationapp.exception.UserAlreadyExistsException;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User registerUser(String email, String fullName, String password) {

    if (email.isEmpty() || password.isEmpty()) {
      throw new IllegalArgumentException("Email and password are required for registration");
    }

    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email format");
    }

    if (userRepository.existsByEmail(email)) {
      throw new UserAlreadyExistsException("User with this email already exists");
    }

    User user = new User();
    user.setEmail(email);
    user.setFullName(fullName);
    user.setPassword(password);
    return userRepository.save(user);
  }

  private boolean isValidEmail(String email) {
    return email != null && email.matches("^[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Z|a-z]{2,}$");
  }
}
