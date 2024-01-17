package com.wizy.educationapp.controller;

import com.wizy.educationapp.exception.ResourceAlreadyExistsException;
import com.wizy.educationapp.model.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.wizy.educationapp.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse>
      resourceAlreadyExistsException(ResourceAlreadyExistsException ex) {

    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse errorResponse = new ErrorResponse(status.name(), ex.getMessage());
    return ResponseEntity.status(status).body(errorResponse);
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
