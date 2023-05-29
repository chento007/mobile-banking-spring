package com.co.mobile_banking.api.account.web;

import com.co.mobile_banking.api.account.AccountService;
import com.co.mobile_banking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;
    @GetMapping
    BaseRest<?> selectAll(@RequestParam(name = "page" ,required = false,defaultValue = "2") int page,
                          @RequestParam(name = "limit", required = false,defaultValue = "2") int limit,
                          @RequestParam(name = "name",required = false,defaultValue = "")String name){
        PageInfo<AccountDto> pageInfo=accountService.findAll(page,limit,name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Accounts has been found")
                .timestamp(LocalDateTime.now())
                .data(pageInfo)
                .build();
    }
    @PostMapping
    BaseRest<?> addAccount(@RequestBody @Valid CreateAccountDto createAccountDto){
        AccountDto accountDto=accountService.insert(createAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been create successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
    @GetMapping("/{id}")
    BaseRest<?> findById(@PathVariable("id") Integer id){
        AccountDto accountDto=accountService.findById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been found successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
    @PutMapping("/{id}")
    BaseRest<?> updateById(@PathVariable("id") Integer id,@RequestBody @Valid UpdateAccountDto updateAccountDto){
        AccountDto resultAccountDto=accountService.update(id,updateAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been update successfully")
                .timestamp(LocalDateTime.now())
                .data(resultAccountDto)
                .build();
    }
    @DeleteMapping("/{id}")
    BaseRest<?> deleteById(@PathVariable("id") Integer id){
        Integer deleteByIdResult=accountService.deleteById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been delete successfully")
                .timestamp(LocalDateTime.now())
                .data(deleteByIdResult)
                .build();
    }
}
