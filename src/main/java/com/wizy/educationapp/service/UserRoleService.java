package com.wizy.educationapp.service;

import com.wizy.educationapp.web.dto.UserRoleRequestDto;
import com.wizy.educationapp.web.dto.UserRoleResponseDto;

public interface UserRoleService {
  UserRoleResponseDto assignRole(UserRoleRequestDto roleDto);

  UserRoleResponseDto removeRole(UserRoleRequestDto roleDto);
}
