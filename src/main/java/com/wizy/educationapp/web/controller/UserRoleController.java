package com.wizy.educationapp.web.controller;

import com.wizy.educationapp.service.UserRoleService;
import com.wizy.educationapp.web.dto.UserRoleRequestDto;
import com.wizy.educationapp.web.dto.UserRoleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/v1/roles")
public class UserRoleController {
  private final UserRoleService userRoleService;

  @PostMapping("/assign")
  public UserRoleResponseDto assignRole(
          @Valid @RequestBody UserRoleRequestDto roleDto) {

    return userRoleService.assignRole(roleDto);
  }

  @PostMapping("/remove")
  public UserRoleResponseDto removeRole(
          @Valid @RequestBody UserRoleRequestDto roleDto) {

    return userRoleService.removeRole(roleDto);
  }
}
