package com.co.mobile_banking.api.useraccounts.web;

import com.co.mobile_banking.api.useraccounts.UserAccountService;
import com.co.mobile_banking.base.BaseRest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/userAccount")
public class UserAccountRestController {
    private final UserAccountService userAccountService;

    public UserAccountRestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    BaseRest<?> findAll(@RequestParam(defaultValue = "2", required = false, name = "page") int page,
                        @RequestParam(defaultValue = "2", required = false, name = "limit") int limit) {
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("UserAccount has been found success")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.findAll(page, limit))
                .build();
    }

    @GetMapping("/{id}")
    BaseRest<?> findById(@PathVariable("id") Integer id) {
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("UserAccount has been found success")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.findById(id))
                .build();
    }

    @PutMapping("/{id}/is-disabled")
    BaseRest<?> disableUserAccount(@PathVariable("id") Integer id, @RequestBody UpdateIsDisabledById updateIsDisabledById) {
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("UserAccount has been found success")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.updateIsDisabled(id, updateIsDisabledById))
                .build();
    }

    @DeleteMapping("/{id}")
    BaseRest<?> deleteById(@PathVariable("id") Integer id) {
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("UserAccount has been found success")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.deleteUserAccountById(id))
                .build();
    }
    @PostMapping
    BaseRest<?> insert(@RequestBody CreateUserAccountDto createUserAccountDto) {
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("UserAccount has been add success")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.addUserAccount(createUserAccountDto))
                .build();
    }
}
