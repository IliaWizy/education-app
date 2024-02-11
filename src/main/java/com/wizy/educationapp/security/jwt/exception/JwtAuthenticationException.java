package com.wizy.educationapp.security.jwt.exception;

public class JwtAuthenticationException extends RuntimeException {
  public JwtAuthenticationException(String message) {
    super(message);
  }
}
