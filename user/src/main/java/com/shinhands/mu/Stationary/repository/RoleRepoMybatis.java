package com.shinhands.mu.Stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRepoMybatis {
    @Select("select r.name_role\n" +
            "from role r\n" +
            "join user_role ur on r.id = ur.id_role\n" +
            "where ur.id_account = #{id} and r.deleted = 0")
    List<String> getRolesByAccountId(Long id);
}
