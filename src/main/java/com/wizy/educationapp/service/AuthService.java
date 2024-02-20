package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.AuthJwtResponseDto;
import com.wizy.educationapp.web.dto.AuthRequestDto;

public interface AuthService {
  AuthJwtResponseDto login(AuthRequestDto request);

  AuthJwtResponseDto refresh(String refreshToken);
}
