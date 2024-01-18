package com.wizy.educationapp.service;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface EmailService {
  void sendConfirmationEmail(String email, Context context) throws MessagingException;
}
