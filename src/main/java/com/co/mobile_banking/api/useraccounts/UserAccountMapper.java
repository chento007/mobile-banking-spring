package com.co.mobile_banking.api.useraccounts;

import com.co.mobile_banking.api.account.Account;
import com.co.mobile_banking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserAccountMapper {
    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectAllSql")
    @Results(
            id = "resultsUserAccount", value = {
            @Result(
                    property = "user", column = "user_id",
                    one = @One(select = "com.co.mobile_banking.api.user.UserMapper.findById")
            ),
            @Result(
                    property = "account", column = "account_id",
                    one = @One(select = "com.co.mobile_banking.api.account.AccountMapper.selectById")
            ),
            @Result(
                    property = "isDisabled", column = "is_disabled"
            )
    })
    List<UserAccount> selectAll();

    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectByIdSql")
    @ResultMap("resultsUserAccount")
    Optional<UserAccount> selectById(Integer id);

    @Select("SELECT EXISTS ( SELECT *FROM user_accounts WHERE id=#{id} AND is_disabled=false)")
    boolean isIdExist(Integer id);

    @UpdateProvider(type = UserAccountProvider.class, method = "buildUpdateIsDisabledByIdSql")
    void update(Integer id, @Param("u") UserAccount userAccount);

    @DeleteProvider(type = UserAccountProvider.class, method = "buildDeleteUserAccountByIdSql")
    void delete(Integer id);

    @InsertProvider(type = UserAccountProvider.class, method = "buildInsertUserAccountSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") UserAccount userAccount);

    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectAccountFromUserIdSql")
    @Results(
            id = "accountMapper",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "account_no", property = "accountNo"),
                    @Result(column = "account_name", property = "accountName"),
                    @Result(column = "phone_number", property = "phoneNumber"),
                    @Result(column = "transfer_limit", property = "transferLimit"),
                    @Result(column = "account_type", property = "accountType",
                            javaType = AccountType.class,
                            one = @One(
                                    select = "com.co.mobile_banking.api.accounttype.AccountTypeMapper.findAccountType"
                            )
                    )
            }
    )
    List<Account> getAllUsers(@Param("id") Integer id);
}