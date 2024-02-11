package com.wizy.educationapp.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(
        @NotBlank(message = "Refresh token cannot be blank")
        String refreshToken
) {
}
