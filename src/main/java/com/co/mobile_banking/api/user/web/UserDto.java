package com.co.mobile_banking.api.user.web;

import com.co.mobile_banking.api.user.Role;
import lombok.Builder;

import java.util.List;

public record UserDto(
        String name,
        String gender,
        String studentCardId,
        Boolean isStudent
) {}
