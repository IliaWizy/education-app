package com.wizy.educationapp.web.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record UserRoleRequestDto(
    @NotNull
    Long userId,

    @NotNull
    @Enumerated(EnumType.STRING)
    UserRoleEnum role
) {
}
