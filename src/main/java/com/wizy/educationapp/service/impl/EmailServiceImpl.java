package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.exception.ResourceNotFoundException;
import com.wizy.educationapp.model.EmailVerificationToken;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.repository.UserRepository;
import com.wizy.educationapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserRepository userRepository;
  private final JavaMailSender javaMailSender;

  public User sendMail(UserDto userDto) {
    User user = userRepository.findByEmail(userDto.email())
            .orElseThrow(ResourceNotFoundException::new);

    EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository
            .findByUser(user)
            .orElseThrow(ResourceNotFoundException::new);

    String token = emailVerificationToken.getToken();
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("education-app");
    message.setTo(user.getEmail());
    message.setSubject("email confirmation");
    message.setText("http://localhost:8080/api/v1/activation?token=" + token);
    javaMailSender.send(message);
    return user;
  }
}
