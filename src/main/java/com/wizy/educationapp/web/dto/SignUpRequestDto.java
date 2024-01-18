package com.wizy.educationapp.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(

    @Email(message = "Invalid email address")
    String email,

    @NotBlank(message = "firstname must NOT be null or empty")
    String firstName,

    @NotBlank(message = "lastname must NOT be null or empty")
    String lastName,

    @NotBlank(message = "password must NOT be null or empty")
    String password) {
}