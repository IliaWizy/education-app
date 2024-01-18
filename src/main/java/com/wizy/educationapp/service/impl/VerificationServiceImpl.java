package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.VerificationService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserRepository userRepository;

  @Override
  public User verification(String token) {
    Optional<EmailVerificationToken> emailVerificationToken =
        emailVerificationTokenRepository.findByToken(token);

    EmailVerificationToken verificationToken = emailVerificationToken.get();

    if (verificationToken.getExpirationTime().after(Timestamp.valueOf(LocalDateTime.now()))) {
      User savedUser = verificationToken.getUser();
      savedUser.setActive(true);

      userRepository.save(savedUser);

      return savedUser;
    } else {
      log.error("Token expiration time has expired {}", token);
      throw new RuntimeException("Token expiration time has expired");
    }
  }
}
