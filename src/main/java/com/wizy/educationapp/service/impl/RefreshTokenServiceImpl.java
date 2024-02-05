package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.config.security.jwt.UserAuthProvider;
import com.wizy.educationapp.database.entity.RefreshToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.RefreshTokenRepository;
import com.wizy.educationapp.service.RefreshTokenService;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.service.exception.TokenNotFoundException;
import com.wizy.educationapp.web.dto.JwtResponseDto;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  @Value("${spring.security.jwt.refresh-token-expiration-time}")
  private Duration jwtTokenRefresh;

  private final UserService userService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final UserAuthProvider userAuthProvider;

  @Override
  public RefreshToken create(String username) {

    User user = userService.getByEmail(username);

    return createOrUpdateRefreshToken(user);
  }

  private RefreshToken createOrUpdateRefreshToken(User user) {
    RefreshToken refreshToken = user.getRefreshToken();

    if (refreshToken == null) {
      refreshToken = new RefreshToken(
          null,
          generateRefreshTokenValue(),
          user,
          new Date(System.currentTimeMillis() + jwtTokenRefresh.toMillis()));
    }

    user.setRefreshToken(refreshToken);


    return refreshTokenRepository.save(refreshToken);
  }

  @Override
  public JwtResponseDto verify(String tokenRequest) {
    RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(tokenRequest)
        .orElseThrow(() -> new TokenNotFoundException("Token does not exist"));

    checkTokenExpiration(refreshToken);

    refreshToken.updateTokenAndExpiration(
        generateRefreshTokenValue(),
        new Date(System.currentTimeMillis() + jwtTokenRefresh.toMillis())
    );

    User user = refreshToken.getUser();
    String token = userAuthProvider.generateToken(user);

    refreshTokenRepository.save(refreshToken);

    return new JwtResponseDto(token, refreshToken.getRefreshToken());
  }

  private void checkTokenExpiration(RefreshToken refreshToken) {
    if (refreshToken.getExpirationTime().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
      refreshTokenRepository.delete(refreshToken);
      throw new TokenExpirationTimeException("Refresh token Expired");
    }
  }

  private String generateRefreshTokenValue() {
    return UUID.randomUUID().toString();
  }
}
