package com.wizy.educationapp.service;

import com.wizy.educationapp.model.User;

public interface UserService {
  User getById(Long id);

  User getByEmail(String email);

  void create(User user);
}
