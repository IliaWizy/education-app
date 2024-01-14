package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.exception.ResourceAlreadyExistsException;
import com.wizy.educationapp.exception.ResourceNotFoundException;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.repository.UserRepository;
import com.wizy.educationapp.service.EmailService;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.service.VerificationTokenService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final VerificationTokenService verificationTokenService;
  private final EmailService emailService;

  @Override
  public User getById(Long id) {
    if (userRepository.findById(id).isEmpty()) {
      throw new ResourceNotFoundException("User doesn't exists");
    }
    return userRepository.findById(id).get();
  }

  @Override
  public User getByEmail(String email) {
    if (userRepository.findByEmail(email).isEmpty()) {
      throw new ResourceNotFoundException("User doesn't exists");
    }
    return userRepository.findByEmail(email).get();
  }

  @Override
  public void create(User user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException("User with this email already exists");
    }
    String token = UUID.randomUUID().toString();
    verificationTokenService.save(user, token);
    userRepository.save(user);
    emailService.sendMail(user);
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }
}
