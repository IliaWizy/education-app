package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import java.sql.Timestamp;
import java.util.Calendar;
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
  private int expirationTime;

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;

  @Override
  public EmailVerificationToken save(User savedUser) {
    String token = UUID.randomUUID().toString();

    return emailVerificationTokenRepository.save(EmailVerificationToken.builder()
        .token(token)
        .user(savedUser)
        .expirationTime(calculateExpiryDate(expirationTime))
        .build());
  }

  private Timestamp calculateExpiryDate(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, hour);
    return new Timestamp(calendar.getTime().getTime());
  }
}
