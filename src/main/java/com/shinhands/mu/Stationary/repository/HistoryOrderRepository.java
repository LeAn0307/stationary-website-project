package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.entity.Bill;
import com.shinhands.mu.Stationary.entity.BillDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryOrderRepository extends JpaRepository<BillDetail, Long> {
    @Query(name = "find_all_order_one_customer", nativeQuery = true)
    public List<HistoryOrderDTO> findAllByCustomer(long customerId);
}
