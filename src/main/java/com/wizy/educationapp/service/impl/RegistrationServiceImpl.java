package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.entity.UserRole;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.database.repository.UserRoleRepository;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.UserRoleEnum;
import com.wizy.educationapp.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRoleRepository userRoleRepository;

  @Transactional
  @Override
  public User register(SignUpRequestDto requestDto) {
    userRepository.findByEmail(requestDto.email()).ifPresent(existingUser -> {
      throw new UserIsExistingException(
          "User already exists.", existingUser.getEmail());
    });

    User savedUser = userMapper.toEntity(requestDto);

    savedUser.setPassword(passwordEncoder.encode(savedUser.getPassword()));

    savedUser.setActive(false);

    userRepository.save(savedUser);

    UserRole role = new UserRole(savedUser, UserRoleEnum.ROLE_USER.name());

    userRoleRepository.save(role);

    return savedUser;
  }
}
