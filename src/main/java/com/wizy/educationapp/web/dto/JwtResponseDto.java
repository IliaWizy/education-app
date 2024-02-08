package com.wizy.educationapp.web.dto;

public record JwtResponseDto(
    String token,
    String refreshToken
) {
}
