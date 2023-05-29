package com.co.mobile_banking.api.useraccounts;

import com.co.mobile_banking.api.user.User;
import com.co.mobile_banking.api.useraccounts.web.CreateUserAccountDto;
import com.co.mobile_banking.api.useraccounts.web.UpdateIsDisabledById;
import com.co.mobile_banking.api.useraccounts.web.UserAccountDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserAccountService {
    PageInfo<UserAccountDto> findAll(int page,int limit);
    UserAccountDto findById(Integer id);
    UpdateIsDisabledById updateIsDisabled(Integer id, UpdateIsDisabledById updateIsDisabledById);
    Integer deleteUserAccountById(Integer id);
    UserAccountDto addUserAccount(CreateUserAccountDto createUserAccountDto);
}
