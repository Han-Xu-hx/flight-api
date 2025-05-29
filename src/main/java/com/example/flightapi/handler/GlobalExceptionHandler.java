/**
 * Global exception handler for handling all exceptions thrown in the application.
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/26.
 */
package com.example.flightapi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.flightapi.common.BaseResponse;

import jakarta.persistence.EntityNotFoundException;

/**
 * Global exception handler for handling all exceptions thrown in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle entity not found exception.
     * @param ex
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
           .body(BaseResponse.error(404, ex.getMessage()));
    }

    /**
     * Handle generic exception.
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
           .body(BaseResponse.error(500, ex.getMessage()));
    }
}
