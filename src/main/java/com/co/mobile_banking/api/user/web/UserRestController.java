package com.co.mobile_banking.api.user.web;

import com.co.mobile_banking.api.user.UserService;
import com.co.mobile_banking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
public class UserRestController {
    private final UserService userService;

    @PostMapping
    BaseRest<?> insert(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto = userService.insertUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been add success")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @GetMapping("/{id}")
    BaseRest<?> findById(@PathVariable("id") Integer id) {
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been found")
                .data(userDto)
                .build();
    }

    @DeleteMapping("/{id}")
    BaseRest<?> deleteById(@PathVariable("id") Integer id) {
        Integer deletedId = userService.deleteByUserId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been delete successfully")
                .data(deletedId)
                .build();
    }

    @PutMapping("/{id}/update-is-deleted")
    BaseRest<?> updateIsDeletedById(@PathVariable("id") Integer id, @RequestBody @Valid IsDeletedDto isDeleted) {
        Integer deletedId = userService.updateIsDeleteById(id, isDeleted.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been deleted successfully")
                .data(deletedId)
                .build();
    }

    @GetMapping("")
    public BaseRest<?> findAllUser(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                   @RequestParam(required = false, name = "limit", defaultValue = "20") int limit,
                                   @RequestParam(required = false, name = "name", defaultValue = "") String name
    ) {
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUser(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Users has been found")
                .data(userDtoPageInfo)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUser(@PathVariable("id") Integer id,
                                  @RequestBody UpdateUserDto updateUserDto) {
        UserDto userDto = userService.updateUser(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User update has been update success")
                .data(userDto)
                .build();
    }

    @GetMapping("/{studentId}/student-card-id")
    public BaseRest<?> findByStudentCardId(@PathVariable("studentId") String studentId) {
        System.out.println(studentId);
        UserDto userDto = userService.findByStudentCardId(studentId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User search has been update success")
                .data(userDto)
                .build();
    }

    @GetMapping("/user-with-user-account")
    public BaseRest<?> findAllUserWithUserAccount(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                                  @RequestParam(required = false, name = "limit", defaultValue = "20") int limit,
                                                  @RequestParam(required = false, name = "name", defaultValue = "") String name) {
        PageInfo<UserJoinUserAccountDto> userJoinUserAccountDtoPageInfo = userService.findAllUserWithUserAccount(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User search has been update success")
                .data(userJoinUserAccountDtoPageInfo)
                .build();

    }

}
