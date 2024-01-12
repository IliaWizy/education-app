package com.wizy.educationapp.repository;

import com.wizy.educationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  boolean existsByEmail(String email);
}
