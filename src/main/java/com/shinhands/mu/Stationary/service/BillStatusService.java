package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.BillStatusDTO;

import java.util.List;

public interface BillStatusService {
    public String getStatus(long id);

    public List<BillStatusDTO> getAllBillStatus();
}
