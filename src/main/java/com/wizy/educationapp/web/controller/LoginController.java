package com.wizy.educationapp.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
public class LoginController {

  @GetMapping("/login")
  public ResponseEntity<String> hello() {
    String message = "Hello Login";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }
}
