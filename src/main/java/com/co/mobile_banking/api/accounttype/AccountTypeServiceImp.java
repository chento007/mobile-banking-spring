package com.co.mobile_banking.api.accounttype;

import com.co.mobile_banking.api.accounttype.web.AccountTypeDto;
import com.co.mobile_banking.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService {
    private final AccountTypeMapStruct mapStruct;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public PageInfo<AccountTypeDto> findAll(int page, int limit, String name) {
        PageInfo<AccountType> accountTypePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> accountTypeMapper.select(name)
        );
        if (!name.isEmpty() && accountTypePageInfo.getList().size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("AccountType with name %s is not found", name)
            );
        } else {
            return mapStruct.acccountTypePageToAccountTypeDtoPageInfo(accountTypePageInfo);
        }
    }

    @Override
    public AccountTypeDto findById(Integer id) {
        AccountType accountType = accountTypeMapper.findAccountTypeById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("AccountType with id %d is not found", id)
                )
        );
        return mapStruct.accountTypeToAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto add(AccountTypeDto accountTypeDto) {
        AccountType accountType = mapStruct.createAccountType(accountTypeDto);
        accountTypeMapper.insert(accountType);
        return mapStruct.accountTypeToAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto update(Integer id, AccountTypeDto accountTypeDto) {
        AccountType accountType = accountTypeMapper.findAccountTypeById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("AccountType with id %d is not found", id)
                )
        );
        accountType = mapStruct.createAccountType(accountTypeDto);
        accountType.setId(id);
        accountTypeMapper.update(accountType);
        System.out.println(accountType);
        return mapStruct.accountTypeToAccountTypeDto(accountType);
    }

    @Override
    public Integer deleteById(Integer id) {
        boolean isIdExist = accountTypeMapper.isIdExist(id);
        if (isIdExist) {
            accountTypeMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("AccountType with id %d is not found", id)
        );
    }
}
