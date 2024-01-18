package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.ActivationResponse;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import jakarta.mail.MessagingException;

public interface RegistrationFacade {
  SignUpResponseDto registeredUserAndSendEmail(SignUpRequestDto signUpRequestDto)
      throws MessagingException;

  ActivationResponse verification(String token);
}
