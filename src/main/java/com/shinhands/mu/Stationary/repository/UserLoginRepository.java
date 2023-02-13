package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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

    @Select("select uw.id, uw.user_name, uw.address, uw.phone, uw.idaccount\n" +
            "from user_website uw\n" +
            "join account a on uw.idaccount = a.id\n" +
            "where a.deleted = 0 and uw.deleted = 0 and a.email = #{email} and a.account_password = #{password}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "address", column = "address"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "idAccount", column = "idaccount")
    })
    UserDTO getUserByAccount(String email, String password);
}
