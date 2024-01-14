package com.wizy.educationapp.controller;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.dto.mappers.UserMapper;
import com.wizy.educationapp.dto.validation.OnCreate;
import com.wizy.educationapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {
  private final UserServiceImpl userService;
  private final UserMapper userMapper;

  @PostMapping("/auth/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerUser(@Validated(OnCreate.class) @RequestBody UserDto dto) {
    userService.create(userMapper.toEntity(dto));
  }
}