package com.co.mobile_banking.api.account.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public record CreateAccountDto(
        @NotBlank(message = "AccountNo is require")
        String accountNo,
        @NotBlank(message = "Account Name is require")
        String accountName,
        String profile,
        @NotNull(message = "Pin is require")
        Integer pin,
        @NotBlank(message = "Password is require")
        String password,
        @NotBlank(message = "Phone Number is require")
        String phoneNumber,
        Integer transferLimit,
        @NotNull(message = "accountType is require")
        Integer accountType
        ) {
}
