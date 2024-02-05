package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.config.security.jwt.UserAuthProvider;
import com.wizy.educationapp.database.entity.RefreshToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.service.LoginService;
import com.wizy.educationapp.service.RefreshTokenService;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.web.dto.JwtRequestDto;
import com.wizy.educationapp.web.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final UserService userService;
  private final UserAuthProvider userAuthProvider;
  private final RefreshTokenService refreshTokenService;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public JwtResponseDto login(JwtRequestDto request) {
    User user = userService.getByEmail(request.email());

    checkUserActivation(user);

    if (passwordEncoder.matches(request.password(), user.getPassword())) {
      String token = userAuthProvider.generateToken(user);

      RefreshToken refreshToken = refreshTokenService.create(request.email());

      return new JwtResponseDto(token, refreshToken.getRefreshToken());
    }

    throw new BadCredentialsException("Invalid password");
  }

  private void checkUserActivation(User user) {
    if (!user.isActive()) {
      throw new BadCredentialsException("You haven't confirmed your email");
    }
  }
}
