package com.wizy.educationapp.service.advice;

import com.wizy.educationapp.service.exception.EmailTokenNotFoundException;
import com.wizy.educationapp.service.exception.MessagingLogicException;
import com.wizy.educationapp.service.exception.RoleNotFoundException;
import com.wizy.educationapp.service.exception.TokenExpirationTimeException;
import com.wizy.educationapp.service.exception.UserIsExistingException;
import com.wizy.educationapp.service.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.wizy.educationapp.controller")
@Slf4j
public class ServiceExceptionHandler {

  @ExceptionHandler(RoleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> roleNotFoundException(RoleNotFoundException ex) {
    log.error(ex.getMessage());
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
          MethodArgumentNotValidException ex
  ) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> globalHandler(Exception ex) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorResponse errorResponse = new ErrorResponse(
            status.name(),
            ex.getMessage() != null ? ex.getMessage() : status.getReasonPhrase()
    );
    return ResponseEntity.status(status).body(errorResponse);
  }

}
