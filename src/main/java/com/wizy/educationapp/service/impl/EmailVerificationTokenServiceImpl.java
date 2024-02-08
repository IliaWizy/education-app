package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import com.wizy.educationapp.service.exception.EmailTokenNotFoundException;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {
  @Value("${spring.application.expiration.token.verification-email}")
  private Duration expirationTime;

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;

  @Override
  public EmailVerificationToken save(User savedUser) {
    String token = UUID.randomUUID().toString();

    return emailVerificationTokenRepository.save(EmailVerificationToken.builder()
        .token(token)
        .user(savedUser)
        .expirationTime(new Date(System.currentTimeMillis() + expirationTime.toMillis()))
        .build());
  }

  @Override
  public EmailVerificationToken findByToken(String token) {
    return emailVerificationTokenRepository.findByToken(token)
        .orElseThrow(() -> new EmailTokenNotFoundException("Token not found"));
  }

  @Override
  public void delete(EmailVerificationToken verificationToken) {
    emailVerificationTokenRepository.delete(verificationToken);
  }
}
