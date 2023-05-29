package com.co.mobile_banking.api.useraccounts;

import com.co.mobile_banking.api.account.Account;
import com.co.mobile_banking.api.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserAccount {
    private Integer id;
    private User user;
    private Account account;
    private Boolean isDisabled;
}
