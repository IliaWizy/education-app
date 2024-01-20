package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.VerificationService;
import com.wizy.educationapp.service.exception.EmailTokenNotFoundException;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.web.dto.ActivationResponse;
import com.wizy.educationapp.web.mapper.VerificationMapper;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserRepository userRepository;

  private final VerificationMapper verificationMapper;

  @Override
  public ActivationResponse verification(String token) {
    EmailVerificationToken verificationToken =
        emailVerificationTokenRepository.findByToken(token).orElseThrow(
            () -> new EmailTokenNotFoundException("Token not found"));

    if (verificationToken.getExpirationTime().after(Timestamp.valueOf(LocalDateTime.now()))) {
      User savedUser = verificationToken.getUser();
      savedUser.setActive(true);


      userRepository.save(savedUser);

      emailVerificationTokenRepository.delete(verificationToken);
      return verificationMapper.toDto(savedUser, "Вы подтвердили почту. Войдите в аккаунт");
    } else {
      emailVerificationTokenRepository.delete(verificationToken);
      throw new TokenExpirationTimeException("Expiration time has expired. Try register again.");
    }
  }
}
