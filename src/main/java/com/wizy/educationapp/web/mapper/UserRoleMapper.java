package com.wizy.educationapp.web.mapper;

import com.wizy.educationapp.database.entity.UserRole;
import com.wizy.educationapp.web.dto.UserRoleResponseDto;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "role", target = "role")
  @Mapping(source = "user.id", target = "userId")
  UserRoleResponseDto toDto(UserRole role);

  Set<UserRoleResponseDto> toDtoSet(Set<UserRole> roles);
}
