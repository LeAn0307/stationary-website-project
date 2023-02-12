package com.shinhands.mu.Stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StatisticRepository {
    @Select("select sum(total) from bill where ID_BILL_STATUS =2 and deleted =0")
    int countTotalBill();
    @Select("select count(1) from bill where ID_BILL_STATUS =2 and deleted =0")
    int countBill();
    @Select("select count(1) from bill where ID_BILL_STATUS in (1,3) and deleted = 0")
    int countOrder();

    @Select("select count(1) from USER_WEBSITE")
    int countUser();
}
