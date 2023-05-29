package com.co.mobile_banking.api.auth;

import com.co.mobile_banking.api.auth.web.RegisterDto;
import com.co.mobile_banking.api.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapStruct {
    User registerDtoToUser(RegisterDto model);
}
