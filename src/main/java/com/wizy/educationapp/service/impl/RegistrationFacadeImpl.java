package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.service.EmailService;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import com.wizy.educationapp.service.RegistrationFacade;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.service.VerificationService;
import com.wizy.educationapp.web.dto.ActivationResponse;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import com.wizy.educationapp.web.mapper.SignUpMapper;
import com.wizy.educationapp.web.mapper.VerificationMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade {
  private final RegistrationService registrationService;
  private final EmailService emailService;
  private final EmailVerificationTokenService emailVerificationTokenService;
  private final VerificationService verificationService;

  private final SignUpMapper signUpMapper;
  private final VerificationMapper verificationMapper;

  @Value("${spring.application.base-url}")
  private String baseUrl;

  @Override
  public SignUpResponseDto registeredUserAndSendEmail(SignUpRequestDto signUpRequestDto)
      throws MessagingException {
    User savedUser = registrationService.register(signUpRequestDto);

    EmailVerificationToken emailVerificationToken = emailVerificationTokenService.save(savedUser);

    sendConfirmationEmail(signUpRequestDto, emailVerificationToken);

    return signUpMapper.toDto(savedUser, "Аккаунт создан. Подтвердите почту.");
  }

  private void sendConfirmationEmail(SignUpRequestDto signUpRequestDto,
                                     EmailVerificationToken emailVerificationToken)
      throws MessagingException {
    Context context = new Context();
    context.setVariable("firstName", signUpRequestDto.firstName());
    context.setVariable("verificationURL",
        baseUrl + "/auth/activation?token=" + emailVerificationToken.getToken());

    emailService.sendConfirmationEmail(signUpRequestDto.email(), context);
  }

  @Override
  public ActivationResponse verification(String token) {
    User verificationUser = verificationService.verification(token);
    return verificationMapper.toDto(verificationUser, "Вы подтвердили почту. Войдите в аккаунт");
  }
}
