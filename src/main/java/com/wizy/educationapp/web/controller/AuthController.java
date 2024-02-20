package com.wizy.educationapp.web.controller;

import com.wizy.educationapp.service.AuthService;
import com.wizy.educationapp.service.RegistrationFacade;
import com.wizy.educationapp.service.VerificationService;
import com.wizy.educationapp.web.dto.AuthJwtResponseDto;
import com.wizy.educationapp.web.dto.AuthRequestDto;
import com.wizy.educationapp.web.dto.RefreshTokenRequestDto;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import com.wizy.educationapp.web.dto.VerificationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

  private final RegistrationFacade registrationFacade;
  private final VerificationService verificationService;
  private final AuthService authService;

  @PostMapping("/register")
  public SignUpResponseDto register(@Valid @RequestBody SignUpRequestDto requestDto) {
    return registrationFacade.registeredUserAndSendEmail(requestDto);
  }

  @GetMapping("/verification")
  public VerificationResponse verification(@RequestParam String token) {
    return verificationService.verification(token);
  }

  @PostMapping("/login")
  public AuthJwtResponseDto login(@Valid @RequestBody AuthRequestDto request) {

    return authService.login(request);
  }

  @PostMapping("/refresh")
  public AuthJwtResponseDto refreshJwtToken(
          @Valid @RequestBody RefreshTokenRequestDto refreshToken) {

    return authService.refresh(refreshToken.refreshToken());
  }
}
