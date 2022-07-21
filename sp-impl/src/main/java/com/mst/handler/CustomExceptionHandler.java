package com.mst.handler;


import com.mst.exception.HttpServiceException;
import com.mst.handler.dto.ServiceErrorResponse;
import com.mst.handler.dto.ValidationErrorResponse;
import com.mst.handler.dto.ValidationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpServiceException.class)
    public ResponseEntity<ServiceErrorResponse> handleException(HttpServiceException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ServiceErrorResponse.builder()
                        .message(exception.getMessage())
                        .exception(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleException(MethodArgumentNotValidException e) {
        List<ValidationErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ValidationErrorResponse errorDto = ValidationErrorResponse.builder()
                    .message(fieldError.getDefaultMessage())
                    .exception(fieldError.getObjectName())
                    .field(fieldError.getField())
                    .build();
            errors.add(errorDto);
        });
        e.getBindingResult().getGlobalErrors().forEach(objectError -> {
            ValidationErrorResponse errorDto = ValidationErrorResponse.builder()
                    .message(objectError.getDefaultMessage())
                    .exception(objectError.getObjectName())
                    .build();
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ValidationExceptionResponse.builder()
                        .errors(errors)
                        .build());
    }
}