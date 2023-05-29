package com.co.mobile_banking.api.accounttype;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private final String tblName="account_types";
    public String buildSelectSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            if(!(name.isEmpty())){
                WHERE("name ILIKE CONCAT('%',#{name},'%')");
            }
        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tblName);
            VALUES("name","#{a.name}");
        }}.toString();
    }
    public String buildUpdateSql(){
        return new SQL(){{
            UPDATE(tblName);
            SET("name=#{a.name}");
            WHERE("id=#{a.id}");
        }}.toString();
    }
    public String buildDeleteSql(){
        return new SQL(){{
            DELETE_FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }
}
