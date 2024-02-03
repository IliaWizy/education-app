package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.Role;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.mapper.SignUpMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final SignUpMapper signUpMapper;

  @Override
  public User register(SignUpRequestDto requestDto) {
    userRepository.findByEmail(requestDto.email()).ifPresent(existingUser -> {
      throw new UserIsExistingException(
          "User already exists.", existingUser.getEmail());
    });

    User savedUser = signUpMapper.toEntity(requestDto);
    savedUser.setPassword(passwordEncoder.encode(requestDto.password()));
    savedUser.setActive(false);
    savedUser.setRoles(Collections.singleton(Role.ROLE_USER));
    userRepository.save(savedUser);

    return savedUser;
  }
}
