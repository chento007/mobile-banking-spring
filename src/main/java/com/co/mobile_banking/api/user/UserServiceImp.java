package com.co.mobile_banking.api.user;

import com.co.mobile_banking.api.user.web.CreateUserDto;
import com.co.mobile_banking.api.user.web.UpdateUserDto;
import com.co.mobile_banking.api.user.web.UserDto;
import com.co.mobile_banking.api.user.web.UserJoinUserAccountDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto insertUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoUser(createUserDto);
        return userMapStruct.userToUserDTO(user);
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)
                )
        );
        return userMapStruct.userToUserDTO(user);
    }

    @Override
    public Integer deleteByUserId(Integer id) {
        Boolean isIdExist = userMapper.exitsById(id);
        if (isIdExist) {
            userMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id)
        );
    }

    @Override
    public Integer updateIsDeleteById(Integer id, Boolean status) {
        Boolean isIdExits = userMapper.exitsById(id);
        if (isIdExits) {
            userMapper.updateIsDelete(id, status);
            System.out.println("Update : " + userMapper.updateIsDeletedTest(id, status));
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id)
        );
    }

    @Override
    public PageInfo<UserDto> findAllUser(int page, int limit, String name) {
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(
                        () -> userMapper.select(name)
                );

        if (!name.isEmpty() && userPageInfo.getList().size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with name %s is not found", name)
            );
        } else {
            System.out.println(userPageInfo);
            return userMapStruct.userPageInfoToUserPageDto(userPageInfo);
        }
    }

    @Override
    public UserDto updateUser(Integer id, UpdateUserDto updateUserDto) {
        User user = userMapper.selectById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)
                )
        );
        user = userMapStruct.updateUserDtoToUser(updateUserDto);
        user.setId(id);
        userMapper.updateById(user);
        return this.findUserById(id);
    }

    @Override
    public UserDto findByStudentCardId(String studentCardId) {
        User user = userMapper.findByStudentCardId(studentCardId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Student Card Id with id %s is not found", studentCardId)
                )
        );
        return userMapStruct.userToUserDTO(user);
    }

    @Override
    public PageInfo<UserJoinUserAccountDto> findAllUserWithUserAccount(int page, int limit, String name) {
        PageInfo<User> userPageInfo=PageHelper.startPage(page,limit).doSelectPageInfo(
                () -> userMapper.selectAllUserJoinUserAccount(name)
        );
        return userMapStruct.userPageInfoToUserPageJoinUserAccountDtoPageInfo(userPageInfo);
    }

}
