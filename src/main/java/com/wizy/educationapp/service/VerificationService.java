package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.VerificationResponse;

public interface VerificationService {
  VerificationResponse verification(String token);
}
