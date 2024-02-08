package com.wizy.educationapp.config.security.jwt.service;

import com.wizy.educationapp.database.entity.User;
import org.springframework.security.core.Authentication;

public interface JwtService {
  String generate(User user);

  Authentication validate(String token);
}
