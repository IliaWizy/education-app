package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.EmailVerificationToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.service.VerificationService;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.web.dto.VerificationResponse;
import com.wizy.educationapp.web.mapper.VerificationMapper;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService {

  private final EmailVerificationTokenService emailVerificationTokenService;
  private final UserService userService;

  private final VerificationMapper verificationMapper;

  @Override
  public VerificationResponse verification(String token) {
    EmailVerificationToken verificationToken =
        emailVerificationTokenService.findByToken(token);

    if (verificationToken.getExpirationTime().after(new Date())) {
      User savedUser = verificationToken.getUser();
      savedUser.setActive(true);


      userService.save(savedUser);

      emailVerificationTokenService.delete(verificationToken);
      return verificationMapper.toDto(savedUser, "Вы подтвердили почту. Войдите в аккаунт");
    } else {
      userService.delete(verificationToken.getUser());
      throw new TokenExpirationTimeException("Expiration time has expired. Try register again.");
    }
  }
}
