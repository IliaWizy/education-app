package com.wizy.educationapp.service;

import com.wizy.educationapp.model.User;
import com.wizy.educationapp.model.VerificationToken;

public interface VerificationTokenService {

  VerificationToken findByToken(String token);

  VerificationToken findByUser(User user);

  void save(User user, String token);
}
