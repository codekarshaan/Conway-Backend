package com.conway.backend.exception;

import com.conway.backend.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDenied(
            AccessDeniedException ex
    ) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Access denied")
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthorizationDenied(
            AuthorizationDeniedException ex
    ) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Access denied")
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(
            BadRequestException ex
    ) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorized(
            UnauthorizedException ex
    ) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<?>>
    handleForbidden(
            ForbiddenException ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.FORBIDDEN
                )
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .timestamp(
                                        LocalDateTime.now()
                                )
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(
            Exception ex
    ) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        String error =
                ex.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(error)
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}