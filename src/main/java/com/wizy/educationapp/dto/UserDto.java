package com.wizy.educationapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizy.educationapp.dto.validation.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserDto(
    @NotNull(message = "Email must not be null", groups = OnCreate.class)
    @NotBlank(message = "Email must not be blank", groups = OnCreate.class)
    @Length(max = 255, message = "Name must be less than 255 symbols", groups = OnCreate.class)
    @Email
    String email,
    @Length(max = 255, message = "FullName must be less than 255 symbols", groups = OnCreate.class)
    String fullName,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must not be null", groups = OnCreate.class)
    @NotBlank(message = "Password must not be blank", groups = OnCreate.class)
    String password
) {
}