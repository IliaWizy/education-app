package com.wizy.educationapp.service.exception;

public class InactiveUserException extends RuntimeException {
  public InactiveUserException(String message) {
    super(message);
  }
}
