package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    public List<Bill> findAllByDateOrderAndDeleted(Date dateOrder, Long deleted);

    public Bill findAllByDeletedAndId(Long deleted, Long id);

    public List<Bill> findByDeleted(Long deleted);

}
