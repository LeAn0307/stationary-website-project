package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillResponseDTO;

import java.util.Date;
import java.util.List;

public interface BillService {

    public List<BillResponseDTO> getAllBills();

    public BillResponseDTO getById(Long id);
//
//    public List<BillDTO> getBillsByOrderDate(Date dateOrder);
//
    public BillDTO addBill(BillDTO billDTO);

    public BillDTO updateBill(long id ,BillDTO billDTO);

    public long deleteBill(long id);

    public boolean updateStatusBill(long id, long statusId);


}
