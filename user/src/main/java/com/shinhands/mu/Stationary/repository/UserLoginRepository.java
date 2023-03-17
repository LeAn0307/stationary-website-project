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
            "join user_website uw on a.id = uw.id_account\n" +
            "join user_role ur on ur.id_user = uw.id\n" +
            "join role r on r.id = ur.id_role\n" +
            "where r.name_role = 'Admin' and a.email = #{email} and a.account_password = #{password}")
    int checkAdmin(String email, String password);

    @Select("select uw.id, uw.user_name, uw.address, uw.phone, uw.id_account\n" +
            "from user_website uw\n" +
            "join account a on uw.id_account = a.id\n" +
            "where a.deleted = 0 and uw.deleted = 0 and a.email = #{email} and a.account_password = #{password}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "address", column = "address"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "idAccount", column = "id_account")
    })
    UserDTO getUserByAccount(String email, String password);

    @Select("select u.id, u.user_name, u.phone,u.address,a.email " +
            "from account a, user_website u " +
            "where u.deleted=0 and u.id_account=a.id and a.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "email", column = "email")
    })
    UserDTO getUserById(Long id);
}
