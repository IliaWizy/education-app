package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;

public interface RegistrationFacade {
  SignUpResponseDto registeredUserAndSendEmail(SignUpRequestDto signUpRequestDto);
}
