package com.co.mobile_banking.api.useraccounts;

import com.co.mobile_banking.api.useraccounts.web.CreateUserAccountDto;
import com.co.mobile_banking.api.useraccounts.web.UpdateIsDisabledById;
import com.co.mobile_banking.api.useraccounts.web.UserAccountDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapstruct {
    PageInfo<UserAccountDto> userAccountPageInfoTouserAccountDtoPageInfo(PageInfo<UserAccount> model);
    UserAccountDto userAccountToUserAccountDto(UserAccount model);
    UserAccount updateUserAccountDisabledToUserAccount(UpdateIsDisabledById model);
}
