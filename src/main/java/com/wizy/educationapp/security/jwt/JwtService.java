package com.wizy.educationapp.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String generateAccessToken(UserDetails userDetails);

  String generateRefreshToken(UserDetails userDetails);

  String extractUsername(String token);

  boolean validateToken(String token);

  void saveRefreshToken(String username, String refreshToken);
}
