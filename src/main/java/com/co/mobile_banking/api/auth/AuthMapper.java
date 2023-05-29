package com.co.mobile_banking.api.auth;

import com.co.mobile_banking.api.user.Authority;
import com.co.mobile_banking.api.user.Role;
import com.co.mobile_banking.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") User user);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    boolean createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Select("SELECT *FROM users WHERE email=#{email} AND is_deleted=FALSE")
    @Results(
            id = "authResultMap",
            value = {
                    @Result(column = "student_card_id", property = "studentCardId"),
                    @Result(column = "is_student", property = "isStudent"),
                    @Result(column = "is_verified", property = "isVerified"),
                    @Result(column = "verified_code", property = "verifiedCode"),
                    @Result(column = "id", property = "roles", many = @Many(select = "loadUserRoles"))
            }
    )
    Optional<User> findEmail(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifiedCodeSql")
    @ResultMap("authResultMap")
    Optional<User> selectByEmailAndVerifiedCode(@Param("email") String email
            , @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildVerifySql")
    @ResultMap("authResultMap")
    void verify(@Param("email") String email
            , @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(
            type = AuthProvider.class,
            method = "buildUpdateVerifiedCodeSql"
    )
    boolean updateVerifiedCode(@Param("email") String email
            , @Param("verifiedCode") String verifiedCode);

    @ResultMap("authResultMap")
    @Select("SELECT *FROM users WHERE email=#{email} AND is_deleted=FALSE AND is_verified=TRUE")
    Optional<User> loadUserByUsername(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserRoleSql")
    @Result(column = "id",property = "authorities",many = @Many(select = "loadUserAuthorities"))
    List<Role> loadUserRoles(@Param("id") Integer id);
    @SelectProvider(type = AuthProvider.class,method = "buildLoadUserAuthoritiesSql")
    List<Authority> loadUserAuthorities(Integer roleId);
}
