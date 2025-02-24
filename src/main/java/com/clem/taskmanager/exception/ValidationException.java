package com.clem.taskmanager.exception;

import com.clem.taskmanager.shared.ApiCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(ExceptionOrder.VALIDATION)
public class ValidationException {
    Map<String, String> errors = new HashMap<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exp) {

        exp.getBindingResult().getFieldErrors().forEach(error -> {
            var field = error.getField().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
            ;
            var errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .code(ApiCode.VALIDATION_ERROR.getCode())
                        .message(ApiCode.VALIDATION_ERROR.getMessage())
                        .errors(errors)
                        .build());
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(UniqueConstraintViolationException exp) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .code(ApiCode.VALIDATION_ERROR.getCode())
                        .error(exp.getMessage())
                        .build());
    }
}
