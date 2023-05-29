package com.co.mobile_banking.api.useraccounts.web;

import java.time.LocalDate;

public record CreateUserAccountDto(
        Integer userId,
        Integer accountId
) {
}
