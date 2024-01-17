package com.wizy.educationapp.controller;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.dto.validation.OnCreate;
import com.wizy.educationapp.service.EmailService;
import com.wizy.educationapp.service.EmailVerificationTokenService;
import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {
  private final UserService userService;
  private final EmailVerificationTokenService emailVerificationTokenService;
  private final EmailService emailService;

  @PostMapping("/auth/sign-up")
  public ResponseEntity<UserDto> registerUser(@Validated(OnCreate.class) @RequestBody UserDto dto) {
    UserDto response = userService.create(dto);

    emailVerificationTokenService.save(dto);

    emailService.sendMail(dto);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}