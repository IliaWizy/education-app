package com.wizy.educationapp.service.exception;

public class RoleIsExistingException extends RuntimeException {
  public RoleIsExistingException(String message) {
    super(message);
  }
}
