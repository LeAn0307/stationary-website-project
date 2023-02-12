package com.shinhands.mu.Stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserLoginRepository {

    @Select("select count(*)\n" +
            "from account a\n" +
            "join user_website uw on a.id = uw.idaccount\n" +
            "join user_role ur on ur.iduser = uw.id\n" +
            "join role r on r.id = ur.id_role\n" +
            "where r.name_role = 'Admin' and a.email = #{email} and a.account_password = #{password}")
    int checkAdmin(String email, String password);
}
