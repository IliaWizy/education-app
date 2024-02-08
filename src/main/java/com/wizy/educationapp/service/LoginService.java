package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.JwtRequestDto;
import com.wizy.educationapp.web.dto.JwtResponseDto;

public interface LoginService {
  JwtResponseDto login(JwtRequestDto request);
}
