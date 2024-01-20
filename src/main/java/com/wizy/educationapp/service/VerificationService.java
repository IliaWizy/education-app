package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.ActivationResponse;

public interface VerificationService {
  ActivationResponse verification(String token);
}
