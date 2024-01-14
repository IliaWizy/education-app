package com.wizy.educationapp.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "verification_tokens")
public class VerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String token;

  @Column(name = "expiry_date")
  private Timestamp expiryDate;

  @OneToOne(cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public VerificationToken(String token, User user) {
    this.token = token;
    this.user = user;
  }
}
