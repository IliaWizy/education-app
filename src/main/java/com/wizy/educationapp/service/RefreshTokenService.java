package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.RefreshToken;
import com.wizy.educationapp.web.dto.JwtResponseDto;

public interface RefreshTokenService {
  RefreshToken create(String email);

  JwtResponseDto verify(String refreshToken);

}
