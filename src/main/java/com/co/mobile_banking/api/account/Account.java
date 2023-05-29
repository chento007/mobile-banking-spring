package com.co.mobile_banking.api.account;

import com.co.mobile_banking.api.accounttype.AccountType;
import com.co.mobile_banking.api.accounttype.web.AccountTypeDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    @NotBlank(message = "AccountNo is require")
    private String accountNo;
    @NotBlank(message = "Account Name is require")
    private String accountName;
    private String profile;
    @NotBlank(message = "Pin is require")
    private Integer pin;
    @NotBlank(message = "Password is require")
    private String password;
    @NotBlank(message = "Phone Number is require")
    private String phoneNumber;
    @NotBlank(message = "TransferLimit is require")
    private Integer transferLimit;
    private AccountType accountType;
}
    