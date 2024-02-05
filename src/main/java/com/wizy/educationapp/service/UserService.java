package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.User;

public interface UserService {
  User getByEmail(String email);

  void checkExists(String email);

  User save(User user);

  void delete(User user);
}
