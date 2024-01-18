package com.wizy.educationapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizy.educationapp.dto.validation.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
    @NotBlank(message = "Email must not be empty", groups = OnCreate.class)
    @Size(max = 255, message = "Name must be less than 255 symbols", groups = OnCreate.class)
    @Email
    String email,
    @Size(max = 255, message = "FullName must be less than 255 symbols", groups = OnCreate.class)
    String fullName,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be empty", groups = OnCreate.class)
    String password
) {
}