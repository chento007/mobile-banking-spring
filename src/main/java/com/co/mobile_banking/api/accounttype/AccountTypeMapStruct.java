package com.co.mobile_banking.api.accounttype;

import com.co.mobile_banking.api.accounttype.web.AccountTypeDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    PageInfo<AccountTypeDto> acccountTypePageToAccountTypeDtoPageInfo(PageInfo<AccountType> accountTypePageInfo);
    AccountTypeDto accountTypeToAccountTypeDto(AccountType accountType);
    AccountType createAccountType(AccountTypeDto accountTypeDto);
}
