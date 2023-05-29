package com.co.mobile_banking.api.user.validator.email;

import com.co.mobile_banking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique,String> {
    // use to check
    private final UserMapper userMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userMapper.exitsByEmail(email);
    }
}
