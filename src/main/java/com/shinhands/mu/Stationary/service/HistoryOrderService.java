package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.entity.Bill;

import java.util.List;

public interface HistoryOrderService {

    public List<HistoryOrderDTO> getAllOrderByCustomer(long customerId);
}
