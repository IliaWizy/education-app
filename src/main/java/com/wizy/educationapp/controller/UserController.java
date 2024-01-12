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
  public User registerUser(@RequestBody RegistrationRequest registrationRequest) {
    return userService.registerUser(
            registrationRequest.getEmail(),
            registrationRequest.getFullName(),
            registrationRequest.getPassword(),
            registrationRequest.isTutor()
    );
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}