package com.wizy.educationapp.controller;

import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ActivationController {

  private final UserService userService;

  @GetMapping("/activation")
  public ResponseEntity<Boolean> activateUser(@RequestParam String token) {

    return new ResponseEntity<>(
            userService.activateUser(token),
            HttpStatus.OK
    );
  }
}
