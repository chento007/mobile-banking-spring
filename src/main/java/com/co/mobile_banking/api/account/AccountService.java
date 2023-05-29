package com.co.mobile_banking.api.account;

import com.co.mobile_banking.api.account.web.AccountDto;
import com.co.mobile_banking.api.account.web.CreateAccountDto;
import com.co.mobile_banking.api.account.web.UpdateAccountDto;
import com.github.pagehelper.PageInfo;

public interface AccountService {
    AccountDto insert(CreateAccountDto createAccountDto);
    AccountDto findById(Integer id);
    PageInfo<AccountDto> findAll(Integer page,Integer limit,String name);
    AccountDto update(Integer id, UpdateAccountDto updateAccountDto);
    Integer deleteById(Integer id);
}
