package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.User;

public interface UserService {
  User getUserByEmail(String email);
}
