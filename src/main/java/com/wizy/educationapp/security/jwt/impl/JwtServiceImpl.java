package com.wizy.educationapp.security.jwt.impl;

import com.wizy.educationapp.database.entity.RefreshToken;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.RefreshTokenRepository;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

  @Value("${spring.security.jwt.secret-key}")
  private String jwtSecret;

  @Value("${spring.security.jwt.expiration-time}")
  private Long jwtExpirationAccess;

  @Value("${spring.security.jwt.refresh-token-expiration-time}")
  private Long jwtExpirationRefresh;

  private final RefreshTokenRepository refreshTokenRepository;

  private final UserRepository userRepository;

  @Override
  public String generateAccessToken(UserDetails userDetails) {
    return generateToken(userDetails, jwtExpirationAccess);
  }

  @Override
  @Transactional
  public String generateRefreshToken(UserDetails userDetails) {
    String refreshToken = generateToken(userDetails, jwtExpirationRefresh);
    saveRefreshToken(userDetails.getUsername(), refreshToken);
    return refreshToken;
  }

  @Override
  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  @Override
  public boolean validateToken(String token) {
    return (extractUsername(token) != null && !isTokenExpired(token));
  }

  @Override
  public void saveRefreshToken(String username, String refreshToken) {
    User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                    "User not found with email: " + username
            ));

    refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

    RefreshToken newRefreshToken = RefreshToken.builder()
            .refreshToken(refreshToken)
            .user(user)
            .expirationTime(extractClaims(refreshToken).getExpiration())
            .build();
    refreshTokenRepository.save(newRefreshToken);
  }

  private String generateToken(UserDetails userDetails, long expirationTime) {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + expirationTime);

    return Jwts.builder()
            .signWith(
                    Keys.hmacShaKeyFor(jwtSecret.getBytes()),
                    Jwts.SIG.HS256)
            .subject(userDetails.getUsername())
            .issuedAt(now)
            .expiration(expirationDate)
            .compact();
  }

  private Claims extractClaims(String token) {
    return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
            .build()
            .parseSignedClaims(token)
            .getPayload();

  }

  private boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

}
