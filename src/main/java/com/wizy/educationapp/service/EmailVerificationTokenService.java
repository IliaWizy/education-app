package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;

public interface EmailVerificationTokenService {
  EmailVerificationToken save(User savedUser);

  EmailVerificationToken findByToken(String token);

  void delete(EmailVerificationToken verificationToken);
}
