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

  public User registerUser(String email, String fullName, String password, boolean isTutor) {

    if (userRepository.existsByEmail(email)) {
      throw new UserAlreadyExistsException("User with this email already exists");
    }

    User user = new User();
    user.setEmail(email);
    user.setFullName(fullName);
    user.setPassword(password);
    user.setTutor(isTutor);
    return userRepository.save(user);
  }
}
