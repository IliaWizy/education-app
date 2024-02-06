package com.wizy.educationapp.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JwtRequestDto(
    @Email(message = "invalid email address")
    String email,

    @NotBlank(message = "password must NOT be null or empty")
    @Size(min = 6, message = "password length must be minimum 6 characters")
    String password
) {
}
