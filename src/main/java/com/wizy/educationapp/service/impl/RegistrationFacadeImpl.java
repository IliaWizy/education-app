package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.service.EmailService;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import com.wizy.educationapp.service.RegistrationFacade;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import com.wizy.educationapp.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade {
  private final RegistrationService registrationService;
  private final EmailService emailService;
  private final EmailVerificationTokenService emailVerificationTokenService;

  private final UserMapper userMapper;


  @Override
  public SignUpResponseDto registeredUserAndSendEmail(SignUpRequestDto signUpRequestDto) {
    User savedUser = registrationService.register(signUpRequestDto);
    EmailVerificationToken emailVerificationToken = emailVerificationTokenService.save(savedUser);
    emailService.sendConfirmationEmail(savedUser.getEmail(), savedUser.getName(),
        emailVerificationToken.getToken());

    return userMapper.toDto(savedUser, "Аккаунт создан. Подтвердите почту.");
  }
}
