package com.co.mobile_banking.api.account;

import com.co.mobile_banking.api.account.web.AccountDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AccountMapstruct {
    AccountDto accountToAccountDto(Account model);
    PageInfo<AccountDto> accountPageInfoToAccountPageInfoDto(PageInfo<Account> model);
}
