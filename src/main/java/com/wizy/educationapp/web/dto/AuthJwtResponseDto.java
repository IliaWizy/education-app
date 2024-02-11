package com.wizy.educationapp.web.dto;

public record AuthJwtResponseDto(
    String accessToken,
    String refreshToken
) {
}
