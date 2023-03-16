package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    public BillDetail findBillDetailByIdAndDeleted(long id, long deleted);

    public List<BillDetail> findByIdBillAndDeleted(Long idBill, Long deleted);
}
