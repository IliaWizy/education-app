package com.wizy.educationapp.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String fullName;
  private String password;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private boolean isTutor;
}
