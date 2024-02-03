package com.wizy.educationapp.service.exception;

public class EmailTokenNotFoundException extends RuntimeException {
  public EmailTokenNotFoundException(String message) {
    super(message);
  }
}
