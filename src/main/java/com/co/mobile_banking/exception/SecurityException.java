package com.co.mobile_banking.exception;

import com.co.mobile_banking.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class SecurityException {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public BaseError<?> handleUnAuthorized(AuthenticationException e) {
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("something when wrong ... please check")
                .timestamp(LocalDateTime.now())
                .erros(e.getMessage())
                .build();
    }
}
