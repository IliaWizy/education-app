package com.wizy.educationapp.repository;

import com.wizy.educationapp.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  //  User save(User user);
  //  @Override
  //  void deleteById(Long id);
}