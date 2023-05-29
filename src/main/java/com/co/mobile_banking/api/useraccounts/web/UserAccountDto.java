package com.co.mobile_banking.api.useraccounts.web;

import com.co.mobile_banking.api.account.Account;
import com.co.mobile_banking.api.account.AccountProvider;
import com.co.mobile_banking.api.user.User;
import lombok.Builder;

import java.util.List;
@Builder
public record UserAccountDto(
        User user,
        Account account
) {
}
