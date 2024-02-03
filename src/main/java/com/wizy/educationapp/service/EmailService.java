package com.wizy.educationapp.service;

public interface EmailService {
  void sendConfirmationEmail(String email, String firstName, String token);
}
