package com.wizy.educationapp.service.exception;

import lombok.Getter;

@Getter
public class UserIsExistingException extends BusinessLogicException {
  private String email;

  public UserIsExistingException(String message, String email) {
    super(message);
    this.email = email;
  }

}
