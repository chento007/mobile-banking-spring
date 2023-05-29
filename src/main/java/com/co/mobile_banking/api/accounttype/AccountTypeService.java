package com.co.mobile_banking.api.accounttype;

import com.co.mobile_banking.api.accounttype.web.AccountTypeDto;
import com.github.pagehelper.PageInfo;

public interface AccountTypeService {
    PageInfo<AccountTypeDto> findAll(int page,int limit,String name);
    AccountTypeDto findById(Integer id);
    AccountTypeDto add(AccountTypeDto accountTypeDto);
    AccountTypeDto update(Integer id,AccountTypeDto accountTypeDto);
    Integer deleteById(Integer id);
}
