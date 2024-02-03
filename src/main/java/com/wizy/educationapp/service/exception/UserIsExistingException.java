package com.wizy.educationapp.service.exception;

import lombok.Getter;

@Getter
public class UserIsExistingException extends RuntimeException {
  private String name;

  public UserIsExistingException(String message, String name) {
    super(message);
    this.name = name;
  }

}
