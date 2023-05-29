package com.co.mobile_banking.api.useraccounts;

import com.co.mobile_banking.api.account.Account;
import com.co.mobile_banking.api.user.User;
import com.co.mobile_banking.api.useraccounts.web.CreateUserAccountDto;
import com.co.mobile_banking.api.useraccounts.web.UpdateIsDisabledById;
import com.co.mobile_banking.api.useraccounts.web.UserAccountDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserAccountServiceImp implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final UserAccountMapstruct userAccountMapstruct;

    public UserAccountServiceImp(UserAccountMapper userAccountMapper, UserAccountMapstruct userAccountMapstruct) {
        this.userAccountMapper = userAccountMapper;
        this.userAccountMapstruct = userAccountMapstruct;
    }

    @Override
    public PageInfo<UserAccountDto> findAll(int page, int limit) {
        PageInfo<UserAccount> userAccountPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                userAccountMapper::selectAll
        );
        return userAccountMapstruct.userAccountPageInfoTouserAccountDtoPageInfo(userAccountPageInfo);
    }

    @Override
    public UserAccountDto findById(Integer id) {
        UserAccount userAccount = userAccountMapper.selectById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User account with id %d is not found !!", id)
                )
        );
        return userAccountMapstruct.userAccountToUserAccountDto(userAccount);
    }

    @Override
    public UpdateIsDisabledById updateIsDisabled(Integer id, UpdateIsDisabledById updateIsDisabledById) {
        boolean isIdExist = userAccountMapper.isIdExist(id);
        if (isIdExist) {
            UserAccount userAccount = userAccountMapstruct.updateUserAccountDisabledToUserAccount(updateIsDisabledById);
            userAccountMapper.update(id, userAccount);
            return updateIsDisabledById;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User account with id %d is not found !!", id)
        );
    }

    @Override
    public Integer deleteUserAccountById(Integer id) {
        System.out.println(id);
        boolean isIdExist = userAccountMapper.isIdExist(id);
        if (isIdExist) {
            userAccountMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User account with id %d is not found !!", id)
        );
    }

    @Override
    public UserAccountDto addUserAccount(CreateUserAccountDto createUserAccountDto) {
        UserAccount userAccount = new UserAccount();
        Account account = new Account();
        User user = new User();
        account.setId(createUserAccountDto.accountId());
        user.setId(createUserAccountDto.userId());
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccountMapper.insert(userAccount);
        System.out.println(userAccount.getId());
        return this.findById(userAccount.getId());
    }
}
