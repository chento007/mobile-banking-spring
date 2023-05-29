package com.co.mobile_banking.api.auth.web;

import com.co.mobile_banking.api.user.validator.email.EmailUnique;
import com.co.mobile_banking.api.user.validator.password.Password;
import com.co.mobile_banking.api.user.validator.password.PasswordMatch;
import com.co.mobile_banking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@PasswordMatch( password = "password", confirmedPassword = "confirmedPassword")
public record RegisterDto(
        @NotBlank(message = "email is require")
        @Email
        @EmailUnique
        String email,
        @NotBlank(message = "password is require")
        @Password
        String password,
        @NotBlank(message = "confirmPassword is require")
        @Password
        String confirmedPassword,
        @NotNull(message = "Roles are require")
        @RoleIdConstraint
        List<Integer> roleIds
) {
}
