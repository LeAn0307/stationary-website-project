package com.shinhands.mu.Stationary.service;


import com.shinhands.mu.Stationary.dto.BillDetailDTO;
import com.shinhands.mu.Stationary.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    public BillDetailDTO addBillDetail(BillDetailDTO billDetailDTO);

    public BillDetailDTO updateBillDetail(long id, BillDetailDTO billDetailDTO);

    public List<BillDetailDTO> getByIdBill(Long idBill);
}
