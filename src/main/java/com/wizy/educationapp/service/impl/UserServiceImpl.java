package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.dto.mappers.UserMapper;
import com.wizy.educationapp.exception.ResourceAlreadyExistsException;
import com.wizy.educationapp.exception.ResourceNotFoundException;
import com.wizy.educationapp.model.User;
import com.wizy.educationapp.repository.EmailVerificationTokenRepository;
import com.wizy.educationapp.repository.UserRepository;
import com.wizy.educationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final EmailVerificationTokenRepository emailVerificationTokenRepository;

  @Override
  public User getById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public UserDto create(UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    if (emailExists(user.getEmail())) {
      throw new ResourceAlreadyExistsException("User with this email already exists");
    }

    user = userRepository.save(user);
    return userMapper.toDto(user);
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public Boolean activateUser(String token) {

    User user = emailVerificationTokenRepository
            .findByToken(token)
            .orElseThrow(ResourceNotFoundException::new)
            .getUser();

    user.setActive(true);
    userRepository.save(user);
    return user.isActive();
  }

  private boolean emailExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
