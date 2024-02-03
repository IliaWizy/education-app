package com.wizy.educationapp.database.repository;

import com.wizy.educationapp.database.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByRefreshToken(String token);
}
