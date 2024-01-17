package com.wizy.educationapp.repository;

import com.wizy.educationapp.model.EmailVerificationToken;
import com.wizy.educationapp.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenRepository
        extends JpaRepository<EmailVerificationToken, Long> {

  Optional<EmailVerificationToken> findByToken(String token);

  Optional<EmailVerificationToken> findByUser(User user);
}
