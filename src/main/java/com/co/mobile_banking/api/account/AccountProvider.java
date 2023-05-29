package com.co.mobile_banking.api.account;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private final String tblName="accounts";
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tblName);
            VALUES("account_no","#{a.accountNo}");
            VALUES("account_name","#{a.accountName}");
            VALUES("profile","#{a.profile}");
            VALUES("pin","#{a.pin}");
            VALUES("password","#{a.password}");
            VALUES("phone_number","#{a.phoneNumber}");
            VALUES("transfer_limit","#{a.transferLimit}");
            VALUES("account_type","#{a.accountType.id}");
        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildSelectSql(@Param("name") String name){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            if(!name.isEmpty()){
                WHERE("account_name ILIKE '%' ||  #{name}  || '%' ");
            }
        }}.toString();
    }
    public String buildUpdateSql(){
        return new SQL(){{
            UPDATE(tblName);
            SET("account_name=#{a.accountName}");
            SET("profile=#{a.profile}");
            SET("phone_number=#{a.phoneNumber}");
            SET("account_type=#{a.accountType.id}");
            WHERE("id=#{a.id}");
        }}.toString();
    }
    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }
}
