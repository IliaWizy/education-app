package com.wizy.educationapp.controller;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.dto.mappers.UserMapper;
import com.wizy.educationapp.dto.validation.OnCreate;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.model.VerificationToken;
import com.wizy.educationapp.service.VerificationTokenService;
import com.wizy.educationapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {
  private final UserServiceImpl userService;
  private final UserMapper userMapper;
  private final VerificationTokenService verificationTokenService;

  @PostMapping("/auth/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public String registerUser(@Validated(OnCreate.class) @RequestBody UserDto dto) {
    userService.create(userMapper.toEntity(dto));
    return "{\"message\": \"Email confirmation message has been sent\"}";
  }

  @GetMapping("/activation")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> activateUser(@RequestParam String token) {
    VerificationToken verificationToken = verificationTokenService.findByToken(token);
    User user = verificationToken.getUser();
    if (user.isActive()) {
      return ResponseEntity.ok("User is already active.");
    } else {
      user.setActive(true);
      userService.save(user);
      return ResponseEntity.ok("User activation success.");
    }
  }
}