package com.wizy.educationapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @EqualsAndHashCode.Include
  private String email;

  private String name;

  private String password;

  private boolean active;

  @OneToMany(mappedBy = "user", orphanRemoval = true)
  private final Set<EmailVerificationToken> emailVerificationTokens = new LinkedHashSet<>();

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  @JsonIgnore
  private final Set<UserRole> roles = new HashSet<>();

}
