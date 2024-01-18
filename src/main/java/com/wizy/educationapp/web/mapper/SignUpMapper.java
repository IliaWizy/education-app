package com.wizy.educationapp.web.mapper;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.web.dto.SignUpRequestDto;
import com.wizy.educationapp.web.dto.SignUpResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignUpMapper {
  @Mapping(source = "email", target = "email")
  @Mapping(source = "firstName", target = "firstname")
  @Mapping(source = "lastName", target = "lastname")
  @Mapping(source = "password", target = "password")

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "active", ignore = true)
  User toEntity(SignUpRequestDto dto);

  @Mapping(source = "entity.email", target = "email")
  @Mapping(source = "entity.firstname", target = "firstName")
  @Mapping(source = "entity.lastname", target = "lastName")
  @Mapping(source = "message", target = "message")
  SignUpResponseDto toDto(User entity, String message);
}
