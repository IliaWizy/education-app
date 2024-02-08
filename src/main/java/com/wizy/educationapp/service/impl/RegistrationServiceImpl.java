package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.Role;
import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.service.RegistrationService;
import com.wizy.educationapp.service.UserService;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.mapper.UserMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Override
  public User register(SignUpRequestDto requestDto) {
    userService.checkExists(requestDto.email());

    User user = userMapper.toEntity(requestDto);
    user.setPassword(passwordEncoder.encode(requestDto.password()));
    user.setAccountExpired(false);
    user.setAccountLocked(false);
    user.setCredentialExpired(false);
    user.setActive(false);
    user.setRoles(Collections.singleton(Role.ROLE_USER));

    return userService.save(user);
  }
}
