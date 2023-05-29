package com.co.mobile_banking.api.user;
import com.co.mobile_banking.api.user.web.CreateUserDto;
import com.co.mobile_banking.api.user.web.UpdateUserDto;
import com.co.mobile_banking.api.user.web.UserDto;
import com.co.mobile_banking.api.user.web.UserJoinUserAccountDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
public interface UserService {
    UserDto insertUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    Integer deleteByUserId(Integer id);
    Integer updateIsDeleteById(Integer id,Boolean status);
    PageInfo<UserDto> findAllUser(int page,int limit,String name);
    UserDto updateUser(Integer id,UpdateUserDto updateUserDto);
    UserDto findByStudentCardId(String studentCardId);
    PageInfo<UserJoinUserAccountDto> findAllUserWithUserAccount (int page,int limit,String name);
}
