package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender emailSender;

  private final TemplateEngine templateEngine;

  @Async
  @Override
  public void sendConfirmationEmail(String email, Context context) throws MessagingException {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(email);
      helper.setSubject("Verification address");

      String htmlContent = templateEngine.process("email-template", context);
      helper.setText(htmlContent, true);

      emailSender.send(message);
    } catch (MessagingException e) {
      log.error("Error sending confirmation email to: {}", email);
      throw new MessagingException();
    }
  }
}
