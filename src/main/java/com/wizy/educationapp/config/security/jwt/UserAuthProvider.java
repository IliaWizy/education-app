package com.wizy.educationapp.config.security.jwt;

import com.wizy.educationapp.config.security.jwt.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {

  @Value("${spring.application.expiration.token.jwt-token}")
  private Long expirationJwt;

  @Value("${spring.application.jwt-secret}")
  private String jwtSecret;

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

  public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
    Map<String, Object> claims = new HashMap<>();

    return Jwts.builder().claims(claims).subject(email)
        .claim("authorities", authorities)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expirationJwt * 60 * 60 * 1000))
        .signWith(generateSecretKey()).compact();
  }
}
