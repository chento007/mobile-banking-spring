package com.co.mobile_banking.api.user;

import com.co.mobile_banking.api.user.web.CreateUserDto;
import com.co.mobile_banking.api.user.web.UpdateUserDto;
import com.co.mobile_banking.api.user.web.UserDto;
import com.co.mobile_banking.api.user.web.UserJoinUserAccountDto;
import com.github.pagehelper.PageInfo;
import lombok.Builder;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoUser(CreateUserDto createUserDto);
    UserDto userToUserDTO(User user);
    List<UserDto> userToUserListDto(List<User> userList);
    User userDtoToUser(UserDto userDto);
    PageInfo<UserDto> userPageInfoToUserPageDto(PageInfo<User> userPageInfo);
    User updateUserDtoToUser(UpdateUserDto userDto);
    PageInfo<UserJoinUserAccountDto> userPageInfoToUserPageJoinUserAccountDtoPageInfo(PageInfo<User> userPageInfo);
}
