package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.model.User;
import com.wizy.educationapp.model.VerificationToken;
import com.wizy.educationapp.service.EmailService;
import com.wizy.educationapp.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final VerificationTokenService verificationTokenService;
  private final JavaMailSender javaMailSender;

  public void sendMail(User user) {
    VerificationToken verificationToken = verificationTokenService.findByUser(user);
    if (verificationToken != null) {
      String token = verificationToken.getToken();
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(user.getEmail());
      message.setSubject("email confirmation");
      message.setText("http://localhost:8080/api/v1/activation?token=" + token);
    }
  }
}
