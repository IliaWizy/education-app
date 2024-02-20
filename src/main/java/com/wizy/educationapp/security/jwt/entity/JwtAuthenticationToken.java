package com.wizy.educationapp.security.jwt.entity;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

  public JwtAuthenticationToken(
          UserDetails principal,
          Object credentials,
          Collection<? extends GrantedAuthority> authorities
  ) {

    super(principal, credentials, authorities);
  }
}
