package com.wizy.educationapp.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('TUTOR')")
@RequestMapping("/v1/tutor")
public class TutorController {
  @GetMapping
  public String tutorAccess() {
    return "Tutor Content";
  }
}
