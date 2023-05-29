package com.co.mobile_banking.api.account;

import com.co.mobile_banking.api.account.web.AccountDto;
import com.co.mobile_banking.api.account.web.CreateAccountDto;
import com.co.mobile_banking.api.account.web.UpdateAccountDto;
import com.co.mobile_banking.api.accounttype.AccountType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountMapstruct accountMapstruct;

    @Override
    public AccountDto insert(CreateAccountDto createAccountDto) {
        Account account = new Account();
        AccountType accountType = new AccountType();
        account.setAccountNo(createAccountDto.accountNo());
        account.setAccountName(createAccountDto.accountName());
        account.setProfile(createAccountDto.profile());
        account.setPin(createAccountDto.pin());
        account.setPassword(createAccountDto.password());
        account.setPhoneNumber(createAccountDto.phoneNumber());
        account.setTransferLimit(createAccountDto.transferLimit());
        accountType.setId(createAccountDto.accountType());
        account.setAccountType(accountType);
        accountMapper.insert(account);
        return this.findById(account.getId());
    }

    @Override
    public AccountDto findById(Integer id) {
        Account account = accountMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account with id %d is not found !", id)
                )
        );
        System.out.println(account);
        return accountMapstruct.accountToAccountDto(account);
    }

    @Override
    public PageInfo<AccountDto> findAll(Integer page, Integer limit, String name) {
        PageInfo<Account> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> accountMapper.select(name)
        );
        return accountMapstruct.accountPageInfoToAccountPageInfoDto(pageInfo);
    }

    @Override
    public AccountDto update(Integer id, UpdateAccountDto updateAccountDto) {
        boolean isIdExist = accountMapper.isIdExist(id);
        if (isIdExist) {
            Account account = new Account();
            AccountType accountType = new AccountType();
            accountType.setId(updateAccountDto.accountType());
            account.setAccountName(updateAccountDto.accountName());
            account.setProfile(updateAccountDto.profile());
            account.setPhoneNumber(updateAccountDto.phoneNumber());
            account.setAccountType(accountType);
            account.setId(id);
            accountMapper.update(account);
            return this.findById(id);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Account with id %d is not found !", id)
        );
    }

    @Override
    public Integer deleteById(Integer id) {
        boolean isIdExist = accountMapper.isIdExist(id);
        if (isIdExist) {
            accountMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Account with id %d is not found !", id)
        );
    }
}
