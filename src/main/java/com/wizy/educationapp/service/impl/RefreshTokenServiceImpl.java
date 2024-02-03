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
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  @Value("${spring.application.expiration.token.jwt-token-refresh}")
  private Long jwtTokenRefresh;

  private final UserService userService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final UserAuthProvider userAuthProvider;

  @Override
  public RefreshToken createRefreshToken(String username) {

    User user = userService.getUserByEmail(username);

    return createOrUpdateRefreshToken(user);
  }

  private RefreshToken createOrUpdateRefreshToken(User user) {
    RefreshToken refreshToken = user.getRefreshToken();

    if (refreshToken == null) {
      refreshToken = RefreshToken.builder()
          .refreshToken(generateRefreshTokenValue())
          .user(user)
          .build();
    }

    refreshToken.setExpirationTime(
        new Timestamp(System.currentTimeMillis() + jwtTokenRefresh * 60 * 60 * 1000));
    user.setRefreshToken(refreshToken);
    refreshTokenRepository.save(refreshToken);

    return refreshToken;
  }

  @Override
  public JwtResponseDto verifyRefreshToken(String tokenRequest) {
    RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(tokenRequest)
        .orElseThrow(() -> new TokenNotFoundException("Token does not exist"));

    checkTokenExpiration(refreshToken);

    refreshToken.setExpirationTime(
        new Timestamp(System.currentTimeMillis() + jwtTokenRefresh * 60 * 60 * 1000));
    refreshToken.setRefreshToken(generateRefreshTokenValue());

    User user = refreshToken.getUser();
    String token = userAuthProvider.generateToken(user.getEmail(), user.getAuthorities());

    refreshTokenRepository.save(refreshToken);

    return new JwtResponseDto(token, refreshToken.getRefreshToken());
  }

  private void checkTokenExpiration(RefreshToken refreshToken) {
    if (refreshToken.getExpirationTime().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
      refreshTokenRepository.delete(refreshToken);
      throw new TokenExpirationTimeException("Refresh token Expired");
    }
  }

  private static String generateRefreshTokenValue() {
    return UUID.randomUUID().toString();
  }
}
