package com.wizy.educationapp.controller;

import lombok.Data;

@Data
public class RegistrationRequest {
  private String email;
  private String fullName;
  private String password;
  private boolean isTutor;
}
