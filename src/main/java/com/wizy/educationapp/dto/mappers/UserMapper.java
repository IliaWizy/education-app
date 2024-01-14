package com.wizy.educationapp.dto.mappers;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "tutor", ignore = true)
  @Mapping(target = "active", ignore = true)
  User toEntity(UserDto dto);
}
