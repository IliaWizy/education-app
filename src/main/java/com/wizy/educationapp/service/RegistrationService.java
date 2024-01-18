package com.wizy.educationapp.service;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.web.dto.SignUpRequestDto;

public interface RegistrationService {
  User register(SignUpRequestDto requestDto);

}
