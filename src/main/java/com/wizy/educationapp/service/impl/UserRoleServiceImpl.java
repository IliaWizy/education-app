package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.entity.UserRole;
import com.wizy.educationapp.database.repository.UserRepository;
import com.wizy.educationapp.database.repository.UserRoleRepository;
import com.wizy.educationapp.service.UserRoleService;
import com.wizy.educationapp.service.exception.InactiveUserException;
import com.wizy.educationapp.service.exception.RoleIsExistingException;
import com.wizy.educationapp.service.exception.RoleNotFoundException;
import com.wizy.educationapp.service.exception.UserNotFoundException;
import com.wizy.educationapp.web.dto.UserRoleRequestDto;
import com.wizy.educationapp.web.dto.UserRoleResponseDto;
import com.wizy.educationapp.web.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRepository userRepository;
  private final UserRoleRepository roleRepository;
  private final UserRoleMapper userRoleMapper;

  @Override
  @Transactional
  public UserRoleResponseDto assignRole(UserRoleRequestDto roleDto) {
    User user = userRepository.findById(roleDto.userId())
            .orElseThrow(() -> new UserNotFoundException("No such User"));

    if (!user.isActive()) {
      throw new InactiveUserException("Cannot assign roles to inactive users.");
    }

    UserRole role = getUserRole(roleDto, user);

    roleRepository.findByUserAndRole(user, role.getRole()).ifPresent(
            userRole -> {
              throw new RoleIsExistingException(
                      "Role already exists"
              );
            }
    );
    return userRoleMapper.toDto(roleRepository.save(role));
  }

  @Override
  @Transactional
  public UserRoleResponseDto removeRole(UserRoleRequestDto roleDto) {
    User user = userRepository.findById(roleDto.userId())
            .orElseThrow(() -> new UserNotFoundException("No such User"));

    UserRole role = getUserRole(roleDto, user);

    return roleRepository.findByUserAndRole(user, role.getRole())
            .map(roleToDelete  -> {
              user.getRoles().remove(roleToDelete);
              roleRepository.delete(roleToDelete);
              return userRoleMapper.toDto(roleToDelete);
            })
            .orElseThrow(() -> new RoleNotFoundException("Role not found for user"));
  }


  private UserRole getUserRole(UserRoleRequestDto roleDto, User user) {

    return UserRole.builder()
            .user(user)
            .role(roleDto.role().toString())
            .build();
  }
}
