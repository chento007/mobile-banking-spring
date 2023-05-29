package com.co.mobile_banking.api.accounttype.web;

import com.co.mobile_banking.api.accounttype.AccountTypeService;
import com.co.mobile_banking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
//    @PreAuthorize("hasAuthority('SCOPE_account:read')")
    @GetMapping
    BaseRest<?> findAll(
            @RequestParam(required = false,name = "page",defaultValue = "1") int page,
            @RequestParam(required = false,name = "limit",defaultValue = "20") int limit,
            @RequestParam(required = false,name = "name" ,defaultValue = "")String name){
        PageInfo<AccountTypeDto> accountTypeDtoPageInfo=accountTypeService.findAll(page,limit,name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("AccountTypes have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoPageInfo)
                .build();
    }
    @GetMapping("/{id}")
    BaseRest<?> findById(@PathVariable("id") Integer id){
        AccountTypeDto accountTypeDto=accountTypeService.findById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("AccountTypes have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @PostMapping
    BaseRest<?> addAccountType(@RequestBody @Valid AccountTypeDto accountTypeDto){
        AccountTypeDto accountTypeDtoResult=accountTypeService.add(accountTypeDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("AccountTypes have been insert success")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoResult)
                .build();
    }
    @PutMapping("/{id}")
    BaseRest<?> updateAccountType(@PathVariable("id") Integer id,@RequestBody @Valid AccountTypeDto accountTypeDto){
        AccountTypeDto accountTypeDtoResult=accountTypeService.update(id,accountTypeDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("AccountTypes have been update success")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoResult)
                .build();
    }
    @DeleteMapping("/{id}")
    BaseRest<?> deleteAccountType(@PathVariable("id") Integer id){
        Integer accountTypeDtoResult=accountTypeService.deleteById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("AccountTypes have been delete success")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoResult)
                .build();
    }
}
