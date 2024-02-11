package com.wizy.educationapp.database.repository;

import com.wizy.educationapp.database.entity.User;
import com.wizy.educationapp.database.entity.UserRole;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

  Optional<UserRole> findByUserAndRole(User user, String role);
}
