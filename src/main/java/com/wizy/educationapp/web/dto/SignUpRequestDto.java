package com.wizy.educationapp.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(

    @Email(message = "invalid email address")
    String email,

    @NotBlank(message = "name must NOT be null or empty")
    String name,

    @NotBlank(message = "password must NOT be null or empty")
    @Size(min = 6, max = 20, message = "password length must be between 6 and 20 characters")
    String password) {
}