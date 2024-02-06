package com.wizy.educationapp.service.exception;

import lombok.Getter;

@Getter
public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
