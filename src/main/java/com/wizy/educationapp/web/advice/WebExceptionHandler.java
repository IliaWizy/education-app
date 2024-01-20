package com.wizy.educationapp.web.advice;

import com.wizy.educationapp.service.exception.EmailTokenNotFoundException;
import com.wizy.educationapp.service.exception.MessagingLogicException;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.wizy.educationapp.web")
@Slf4j
public class WebExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationException(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashedMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));

    log.error("MethodArgumentNotValidException: {}", errors);
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(MessagingLogicException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleMessagingLogicException(
      MessagingLogicException ex) {

    log.error("MessagingException: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(TokenExpirationTimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleTokenExpirationTimeException(
      TokenExpirationTimeException ex) {

    log.error("Token expiration time has expired: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(UserIsExistingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleUserIsExistingException(
      UserIsExistingException ex) {

    log.error(ex.getMessage() + ex.getEmail());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(EmailTokenNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleEmailTokenNotFoundException(
      EmailTokenNotFoundException ex) {

    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
