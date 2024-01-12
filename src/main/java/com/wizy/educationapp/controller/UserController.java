package com.wizy.educationapp.controller;

import com.wizy.educationapp.model.User;
import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public User registerUser(@RequestBody RegistrationRequest registrationRequest) {
    return userService.registerUser(
            registrationRequest.getEmail(),
            registrationRequest.getFullName(),
            registrationRequest.getPassword(),
            registrationRequest.isTutor()
    );
  }
}