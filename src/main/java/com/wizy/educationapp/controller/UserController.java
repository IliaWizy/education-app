package com.wizy.educationapp.controller;

import com.wizy.educationapp.exception.UserAlreadyExistsException;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
    try {
      User user = userService.registerUser(
              registrationRequest.getEmail(),
              registrationRequest.getFullName(),
              registrationRequest.getPassword()
      );

      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }
}