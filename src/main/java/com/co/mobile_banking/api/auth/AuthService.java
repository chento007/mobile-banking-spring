package com.co.mobile_banking.api.auth;

import com.co.mobile_banking.api.auth.web.AuthDto;
import com.co.mobile_banking.api.auth.web.LoginDto;
import com.co.mobile_banking.api.auth.web.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    void verify(String email);
    void checkVerify(String email,String verifiedCode);
    AuthDto login(LoginDto loginDto);
}
