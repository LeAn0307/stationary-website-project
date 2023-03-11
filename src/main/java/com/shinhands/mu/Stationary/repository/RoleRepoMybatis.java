package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RoleRepoMybatis {
    @Select("select r.id, r.name_role, r.deleted\n" +
            "from role r\n" +
            "join user_role ur on r.id = ur.id_role\n" +
            "where ur.id_account = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nameRole", column = "name_role"),
            @Result(property = "deleted", column = "deleted")
    })
    List<Role> getRolesByAccountId(Long id);
}
