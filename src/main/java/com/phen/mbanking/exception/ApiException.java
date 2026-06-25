package com.phen.mbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<?, ?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = new ArrayList<>();

        e.getFieldErrors().forEach(fieldError -> fieldErrors
                .add(FieldError.builder()
                        .field(fieldError.getField())
                        .detail(fieldError.getDefaultMessage())
                        .build())
        );

        return Map.of(
                "error", ErrorResponse
                        .builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .reason(fieldErrors).build()
        );
    }


    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {

        ErrorResponse<String> errorResponse = ErrorResponse.<String>builder()

                .code(e.getStatusCode().value()).reason(e.getReason()).build();

        return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", errorResponse));
    }
}
