package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.Role;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.mapper.SignUpMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
  private final UserRepository userRepository;

  private final SignUpMapper signUpMapper;

  @Override
  public User register(SignUpRequestDto requestDto) {
    User savedUser = signUpMapper.toEntity(requestDto);

    userRepository.findByEmail(savedUser.getEmail()).ifPresent(existingUser -> {
      log.error("User with email" + existingUser.getEmail() + " already exists.");
      throw new IllegalStateException(
          "User with email " + existingUser.getEmail() + " already exists.");
    });

    savedUser.setRoles(Collections.singleton(Role.ROLE_USER));
    savedUser.setActive(false);
    userRepository.save(savedUser);

    return savedUser;
  }
}
