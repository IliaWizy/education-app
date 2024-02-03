package com.wizy.educationapp.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "email_verification_token")
public class EmailVerificationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;

  @Column(name = "token")
  private final String token;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private final User user;

  @Column(name = "expiration_time")
  private final Timestamp expirationTime;

}
