package com.wizy.educationapp.service.exception;

public class EmailTokenNotFoundException extends BusinessLogicException {
  public EmailTokenNotFoundException(String message) {
    super(message);
  }
}
