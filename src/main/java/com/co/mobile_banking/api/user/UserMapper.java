package com.co.mobile_banking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);

    @Results(
            id = "userResultMap",
            value = {
                    @Result(column = "student_card_id", property = "studentCardId"),
                    @Result(column = "is_student", property = "isStudent")
            }
    )
    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    Optional<User> selectById(@Param("id") Integer id);

    @ResultMap("userResultMap")
    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    User findById(Integer id);

    @Select("SELECT EXISTS (SELECT * FROM users WHERE id=#{id}) ")
    Boolean exitsById(@Param("id") Integer id);
    @Select("SELECT EXISTS (SELECT * FROM users WHERE email=#{email}) ")
    Boolean exitsByEmail(@Param("email") String email);
    @Select("SELECT EXISTS (SELECT * FROM roles WHERE id=#{id}) ")
    Boolean exitByRoleId(@Param("id") Integer id);

    @DeleteProvider(type = UserProvider.class, method = "buildDeleteById")
    void delete(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedById")
    void updateIsDelete(@Param("id") Integer id, @Param("status") Boolean status);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedById")
    Integer updateIsDeletedTest(@Param("id") Integer id, @Param("status") Boolean status);

    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User> select(@Param("name") String name);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateSql")
    void updateById(@Param("u") User user);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByStudentCardIdSql")
    @ResultMap("userResultMap")
    Optional<User> findByStudentCardId(@Param("studentCardId") String studentCardId);

    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @Result(column = "student_card_id", property = "studentCardId")
    @Result(column = "is_student", property = "isStudent")
    @Result(property = "accountList", column = "id",
            many = @Many(select = "com.co.mobile_banking.api.useraccounts.UserAccountMapper.getAllUsers"))
    List<User> selectAllUserJoinUserAccount(@Param("name") String name);
}
