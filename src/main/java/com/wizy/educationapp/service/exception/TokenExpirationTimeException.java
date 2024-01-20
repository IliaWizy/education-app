package com.wizy.educationapp.service.exception;

public class TokenExpirationTimeException extends BusinessLogicException {
  public TokenExpirationTimeException(String message) {
    super(message);
  }
}
