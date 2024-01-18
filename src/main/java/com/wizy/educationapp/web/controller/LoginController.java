package com.wizy.educationapp.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

  @GetMapping
  public ResponseEntity<String> hello() {
    String message = "Hello Login";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }
}
