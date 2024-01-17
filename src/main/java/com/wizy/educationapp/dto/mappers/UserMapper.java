package com.wizy.educationapp.dto.mappers;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "email", source = "email")
  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "password", source = "password")
  UserDto toDto(User user);

  @Mapping(target = "email", source = "email")
  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "password", source = "password")
  User toEntity(UserDto dto);
}
