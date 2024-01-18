package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.VerificationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserRepository userRepository;

  @Override
  public User verification(String token) {
    Optional<EmailVerificationToken> emailVerificationToken =
        emailVerificationTokenRepository.findByToken(token);

    EmailVerificationToken verificationToken = emailVerificationToken.get();
    User savedUser = verificationToken.getUser();
    savedUser.setActive(true);

    userRepository.save(savedUser);

    return savedUser;
  }
}
