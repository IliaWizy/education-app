package com.wizy.educationapp.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
  private final String timestamp;

  public UserAlreadyExistsException(String message) {
    super(message);
    this.timestamp = LocalDateTime.now().toString();
  }

  public String getTimestamp() {
    return timestamp;
  }
}
