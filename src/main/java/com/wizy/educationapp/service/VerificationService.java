package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.User;

public interface VerificationService {
  User verification(String token);
}
