package com.co.mobile_banking.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseError<T>(
        Boolean status,
        Integer code,
        String message,
        LocalDateTime timestamp,
        T erros
) {
}