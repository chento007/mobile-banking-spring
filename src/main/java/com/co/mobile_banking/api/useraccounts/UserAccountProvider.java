package com.co.mobile_banking.api.useraccounts;

import org.apache.ibatis.jdbc.SQL;

public class UserAccountProvider {
    private final String tbl = "user_accounts";

    public String buildSelectAllSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tbl);
            WHERE("is_disabled=false");
        }}.toString();
    }

    public String buildSelectByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tbl);
            WHERE("id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildUpdateIsDisabledByIdSql() {
        return new SQL() {{
            UPDATE(tbl);
            SET("is_disabled=#{u.isDisabled}");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildDeleteUserAccountByIdSql() {
        return new SQL() {{
            DELETE_FROM(tbl);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertUserAccountSql() {
        return new SQL() {{
            INSERT_INTO(tbl);
            VALUES("user_id", "#{u.user.id}");
            VALUES("account_id", "#{u.account.id}");
            VALUES("created_at", "CURRENT_TIMESTAMP");
            VALUES("is_disabled", "FALSE");
        }}.toString();
    }

    public String buildSelectAccountFromUserIdSql() {
        return new SQL() {{
            SELECT("accounts .*");
            FROM("""
                    user_accounts INNER JOIN
                    accounts ON user_accounts.account_id = accounts.id
                    """);
            WHERE("user_accounts.user_id=#{id}", "user_accounts.is_disabled = false");

        }}.toString();
    }
}
