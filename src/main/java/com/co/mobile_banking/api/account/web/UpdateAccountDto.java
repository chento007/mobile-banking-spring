package com.co.mobile_banking.api.account.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountDto(
        @NotBlank(message = "name is require")
        String accountName,
        String profile,
        @NotBlank(message = "phone number is require")
        String phoneNumber,
        @NotNull(message = "account type is require")
        Integer accountType
) {
}
