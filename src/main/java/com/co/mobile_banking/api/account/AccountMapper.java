package com.co.mobile_banking.api.account;

import com.co.mobile_banking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AccountMapper {
    @InsertProvider(type = AccountProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("a") Account account);

    @Results(
            id = "accountMapper",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "account_no", property = "accountNo"),
                    @Result(column = "account_name", property = "accountName"),
                    @Result(column = "phone_number", property = "phoneNumber"),
                    @Result(column = "transfer_limit", property = "transferLimit"),
                    @Result(column = "account_type", property = "accountType",
                            one = @One(select = "findAccountTypeById")
                    )
            }
    )
    @SelectProvider(type = AccountProvider.class, method = "buildSelectByIdSql")
    Optional<Account> findById(@Param("id") Integer id);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectByIdSql")
    @ResultMap("accountMapper")
    Account selectById(@Param("id") Integer id);

    @Select("SELECT * FROM account_types WHERE id = #{id}")
    AccountType findAccountTypeById(Integer id);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectSql")
    @ResultMap("accountMapper")
    List<Account> select(@Param("name") String name);

    @UpdateProvider(type = AccountProvider.class, method = "buildUpdateSql")
    void update(@Param("a") Account account);

    @Select("""
            SELECT EXISTS (SELECT *FROM accounts WHERE id=#{id})
            """)
    boolean isIdExist(@Param("id") Integer id);

    @DeleteProvider(type = AccountProvider.class, method = "buildDeleteByIdSql")
    void delete(@Param("id") Integer id);
}
