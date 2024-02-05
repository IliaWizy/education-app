package com.wizy.educationapp.service.advice;

import com.wizy.educationapp.service.exception.EmailTokenNotFoundException;
import com.wizy.educationapp.service.exception.MessagingLogicException;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import com.wizy.educationapp.service.exception.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

  @ExceptionHandler({TokenExpirationTimeException.class, MessagingLogicException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleBadRequestException(
      RuntimeException ex) {

    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(UserIsExistingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleUserIsExistingException(
      UserIsExistingException ex) {

    log.error(ex.getMessage() + ex.getName());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(EmailTokenNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleEmailTokenNotFoundException(
      EmailTokenNotFoundException ex) {

    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleUsernameNotFoundException(
      UsernameNotFoundException ex) {

    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

}
