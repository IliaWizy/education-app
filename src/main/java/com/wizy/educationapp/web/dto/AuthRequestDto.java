package com.wizy.educationapp.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
    @Email(message = "invalid email address")
    String email,

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password
) {
}
