package com.wizy.educationapp.web.controller;

import com.wizy.educationapp.service.LoginService;
import com.wizy.educationapp.service.RefreshTokenService;
import com.wizy.educationapp.service.RegistrationFacade;
import com.wizy.educationapp.service.VerificationService;
import com.wizy.educationapp.web.dto.JwtRequestDto;
import com.wizy.educationapp.web.dto.JwtResponseDto;
import com.wizy.educationapp.web.dto.RefreshTokenRequestDto;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import com.wizy.educationapp.web.dto.VerificationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

  private final RegistrationFacade registrationFacade;
  private final VerificationService verificationService;
  private final LoginService loginService;
  private final RefreshTokenService refreshTokenService;

  @PostMapping("/register")
  public SignUpResponseDto register(@Valid @RequestBody SignUpRequestDto requestDto) {
    return registrationFacade.registeredUserAndSendEmail(requestDto);
  }

  @GetMapping("/verification")
  public VerificationResponse verification(@RequestParam String token) {
    return verificationService.verification(token);
  }

  @PostMapping("/login")
  public JwtResponseDto login(@Valid @RequestBody JwtRequestDto request) {
    return loginService.login(request);
  }

  @PostMapping("/refresh")
  public JwtResponseDto refreshJwtToken(@Valid @RequestBody RefreshTokenRequestDto request) {
    return refreshTokenService.verifyRefreshToken(request.refreshToken());
  }
}
