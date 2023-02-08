package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillStatusRepository extends JpaRepository<BillStatus, Long> {
    public BillStatus findByIdAndDeleted(Long id, Long deleted);
}
