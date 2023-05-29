package com.co.mobile_banking.api.auth.web;

import com.co.mobile_banking.api.user.validator.password.Password;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "email is require")
        String email,
        @NotBlank(message = "password is require")
        @Password
        String password
) {
}
