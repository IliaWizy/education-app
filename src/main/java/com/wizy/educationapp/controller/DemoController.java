package com.wizy.educationapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @RequestMapping("/demo")
  public ResponseEntity<String> demo() {
    String message = "App is running";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }
}
