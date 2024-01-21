package com.wizy.educationapp.service.exception;

public class TokenExpirationTimeException extends RuntimeException {
  public TokenExpirationTimeException(String message) {
    super(message);
  }
}
