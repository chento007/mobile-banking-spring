package com.co.mobile_banking.api.account.web;

import com.co.mobile_banking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseUpdateAccountDto(
        String accountName,
        String profile,
        String phoneNumber,
        AccountType accountType
) {
}
