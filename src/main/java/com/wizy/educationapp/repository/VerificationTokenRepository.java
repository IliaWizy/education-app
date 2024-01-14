package com.wizy.educationapp.repository;

import com.wizy.educationapp.model.User;
import com.wizy.educationapp.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);

  VerificationToken findByUser(User user);
}
