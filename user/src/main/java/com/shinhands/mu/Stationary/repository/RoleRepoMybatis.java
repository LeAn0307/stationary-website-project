package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.RoleDTO;
import com.shinhands.mu.Stationary.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRepoMybatis {
    @Select("select r.id, r.name_role\n" +
            "from role r\n" +
            "join user_role ur on r.id = ur.id_role\n" +
            "where ur.id_account = #{id} and r.deleted = 0")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nameRole", column = "name_role")
    })
    List<RoleDTO> getRolesByAccountId(Long id);
}
