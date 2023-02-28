package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.BillResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillRepositoryMybatis {
    @Select("select b.bill_id, b.date_order, b.total, b.note, b.payment, b.code_momo, b.created_at, b.updated_at, bs.status, uw.user_name\n" +
            "from bill b\n" +
            "join bill_status bs on b.id_bill_status = bs.id\n" +
            "join user_website uw on b.customer_id = uw.id\n" +
            "where b.deleted = 0 and bs.deleted = 0 and uw.deleted = 0\n" +
            "order by b.bill_id")
    @Results(value = {
            @Result(property = "id", column = "bill_id"),
            @Result(property = "dateOrder", column = "date_order"),
            @Result(property = "total", column = "total"),
            @Result(property = "note", column = "note"),
            @Result(property = "payment", column = "payment"),
            @Result(property = "codeMomo", column = "code_momo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "status", column = "status"),
            @Result(property = "customerName", column = "user_name")
    })
    public List<BillResponseDTO> findAll();

    @Select("select b.bill_id, b.date_order, b.total, b.note, b.payment, b.codemomo, b.created_at, b.updated_at, bs.status, uw.user_name\n" +
            "from bill b\n" +
            "join bill_status bs on b.id_bill_status = bs.id\n" +
            "join user_website uw on b.customer_id = uw.id\n" +
            "where b.deleted = 0 and bs.deleted = 0 and uw.deleted = 0 and b.bill_id = #{id}\n" +
            "order by b.bill_id")
    @Results(value = {
            @Result(property = "id", column = "bill_id"),
            @Result(property = "dateOrder", column = "date_order"),
            @Result(property = "total", column = "total"),
            @Result(property = "note", column = "note"),
            @Result(property = "payment", column = "payment"),
            @Result(property = "codeMomo", column = "codemomo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "status", column = "status"),
            @Result(property = "customerName", column = "user_name")
    })
    public BillResponseDTO findById(Long id);
}
