package com.co.mobile_banking.api.accounttype;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select(@Param("name") String name);

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    Optional<AccountType> findAccountTypeById(@Param("id") Integer id);

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    void insert(@Param("a") AccountType accountType);

    @InsertProvider(type = AccountTypeProvider.class, method = "buildUpdateSql")
    void update(@Param("a") AccountType accountType);

    @Select("SELECT EXISTS (SELECT *FROM account_types where id=#{id})")
    boolean isIdExist(@Param("id") Integer id);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    void delete(@Param("id") Integer id);

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    AccountType findAccountType(@Param("id") Integer id);
}
