package com.co.mobile_banking.api.user;

import com.co.mobile_banking.api.account.Account;
import com.co.mobile_banking.api.useraccounts.UserAccount;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class User  {
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isDeleted;
    private Boolean isStudent;
    private List<Account> accountList;
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;
    private List<Role> roles;
}
