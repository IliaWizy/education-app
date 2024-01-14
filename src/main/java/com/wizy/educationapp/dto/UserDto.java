package com.wizy.educationapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizy.educationapp.dto.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {
  @NotNull(message = "Email must not be null", groups = OnCreate.class)
  @NotBlank(message = "Email must not be blank", groups = OnCreate.class)
  @Length(max = 255, message = "Name must be less than 255 symbols", groups = OnCreate.class)
  private String email;

  @Length(max = 255, message = "FullName must be less than 255 symbols", groups = OnCreate.class)
  private String fullName;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull(message = "Password must not be null", groups = OnCreate.class)
  @NotBlank(message = "Password must not be blank", groups = OnCreate.class)
  private String password;
}
