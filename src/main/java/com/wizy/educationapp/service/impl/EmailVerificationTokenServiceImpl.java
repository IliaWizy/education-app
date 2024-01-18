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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;

  @Override
  public EmailVerificationToken save(User savedUser) {
    String token = UUID.randomUUID().toString();

    EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
    emailVerificationToken.setToken(token);
    emailVerificationToken.setUser(savedUser);
    emailVerificationToken.setExpirationTime(calculateExpiryDate(24));

    return emailVerificationTokenRepository.save(emailVerificationToken);
  }

  private Timestamp calculateExpiryDate(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, hour);
    return new Timestamp(calendar.getTime().getTime());
  }
}
