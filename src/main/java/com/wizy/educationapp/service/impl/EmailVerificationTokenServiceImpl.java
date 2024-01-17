package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.exception.ResourceNotFoundException;
import com.wizy.educationapp.model.EmailVerificationToken;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.repository.UserRepository;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserRepository userRepository;

  @Transactional
  public EmailVerificationToken findByToken(String token) {
    return emailVerificationTokenRepository.findByToken(token)
            .orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public EmailVerificationToken findByUser(User user) {
    return emailVerificationTokenRepository.findByUser(user)
            .orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public EmailVerificationToken save(UserDto dto) {
    User user = userRepository.findByEmail(dto.email())
            .orElseThrow(ResourceNotFoundException::new);

    String token = UUID.randomUUID().toString();

    EmailVerificationToken emailVerificationToken = new EmailVerificationToken(token, user);
    emailVerificationToken.setExpiryDate(calculateExpiryDate(1440));

    return emailVerificationTokenRepository.save(emailVerificationToken);
  }

  private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Timestamp(calendar.getTime().getTime());
  }
}
