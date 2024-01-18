package com.wizy.educationapp.web.mapper;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.web.dto.ActivationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerificationMapper {

  @Mapping(source = "entity.email", target = "email")
  @Mapping(source = "message", target = "message")
  ActivationResponse toDto(User entity, String message);
}
