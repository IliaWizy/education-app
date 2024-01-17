package com.wizy.educationapp.service;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.model.User;

public interface UserService {
  User getById(Long id);

  User getByEmail(String email);

  UserDto create(UserDto userDto);

  User save(User user);

  Boolean activateUser(String token);
}
