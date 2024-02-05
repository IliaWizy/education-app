package com.wizy.educationapp.config.security.jwt;

import com.wizy.educationapp.config.security.jwt.service.CustomUserDetailService;
import com.wizy.educationapp.database.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {

  @Value("${spring.security.jwt.secret-key}")
  private String jwtSecret;

  @Value("${spring.security.jwt.expiration-time}")
  private Duration expirationJwt;

  private final CustomUserDetailService customUserDetailService;

  public Authentication validateToken(String token) {
    String username = getClaimFromToken(token, Claims::getSubject);

    UserDetails user = customUserDetailService.loadUserByUsername(username);

    return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(generateSecretKey(), token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(SecretKey key, String token) {
    JwtParser parser = Jwts.parser().verifyWith(key).build();
    return parser.parseSignedClaims(token).getPayload();
  }

  private SecretKey generateSecretKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(User user) {

    return Jwts.builder()
        .signWith(generateSecretKey())
        .subject(user.getEmail())
        .claim("authorities", user.getAuthorities())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expirationJwt.toMillis()))
        .compact();
  }
}
