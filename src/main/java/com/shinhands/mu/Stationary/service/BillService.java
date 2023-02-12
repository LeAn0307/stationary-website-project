package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.BillDTO;

import java.util.Date;
import java.util.List;

public interface BillService {

    public List<BillDTO> getAllBills();

    public BillDTO getBillsByBillId(long billId);

    public List<BillDTO> getBillsByOrderDate(Date dateOrder);

    public Long addBill(BillDTO billDTO);

    public BillDTO updateBill(long id ,BillDTO billDTO);

    public long deleteBill(long id);

    public boolean updateStatusBill(long id, long statusId);


}
