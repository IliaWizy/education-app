package com.wizy.educationapp.web.dto;

public record UserRoleResponseDto(
    Long id,
    String role,
    Long userId
) {
}
