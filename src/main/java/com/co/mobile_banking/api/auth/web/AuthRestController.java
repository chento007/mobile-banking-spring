package com.co.mobile_banking.api.auth.web;

import com.co.mobile_banking.api.auth.AuthService;
import com.co.mobile_banking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto){
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("register successfully")
                .data(registerDto.email())
                .build();
    }
    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam(name = "email") String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("please check your verified email box")
                .data(email)
                .build();
    }
    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email,@RequestParam String verifiedCode){
        authService.checkVerify(email,verifiedCode);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("You have been verified Successfully")
                .data(email)
                .build();
    }
    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LoginDto loginDto){
        var result=authService.login(loginDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("You have been verified Successfully")
                .data(result)
                .build();
    }
}
