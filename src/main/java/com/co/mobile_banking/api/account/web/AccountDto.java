package com.co.mobile_banking.api.account.web;

import com.co.mobile_banking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AccountDto(
         String accountNo,
         String accountName,
         String profile,
         Integer pin,
         String password,
         String phoneNumber,
         Integer transferLimit,
         AccountType accountType
) {
}
