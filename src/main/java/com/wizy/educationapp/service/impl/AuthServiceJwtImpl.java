package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.security.jwt.JwtService;
import com.wizy.educationapp.security.jwt.entity.SecurityUser;
import com.wizy.educationapp.security.jwt.exception.JwtAuthenticationException;
import com.wizy.educationapp.security.jwt.impl.SecurityUserService;
import com.wizy.educationapp.service.AuthService;
import com.wizy.educationapp.web.dto.AuthJwtResponseDto;
import com.wizy.educationapp.web.dto.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceJwtImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;
  private final SecurityUserService securityUserService;

  @Override
  public AuthJwtResponseDto login(AuthRequestDto request) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

    String accessToken = jwtService.generateAccessToken(securityUser);
    String refreshToken = jwtService.generateRefreshToken(securityUser);

    return new AuthJwtResponseDto(accessToken, refreshToken);
  }

  @Override
  public AuthJwtResponseDto refresh(String refreshToken) {
    String username = jwtService.extractUsername(refreshToken);
    UserDetails userDetails = securityUserService.loadUserByUsername(username);

    if (!jwtService.validateToken(refreshToken)) {
      throw new JwtAuthenticationException("Invalid refresh token");
    }

    String newAccessToken = jwtService.generateAccessToken(userDetails);
    String newRefreshToken = jwtService.generateRefreshToken(userDetails);

    jwtService.saveRefreshToken(username, newRefreshToken);

    return new AuthJwtResponseDto(newAccessToken, newRefreshToken);
  }
}
