package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.entity.Bill;
import com.shinhands.mu.Stationary.repository.HistoryOrderRepository;
import com.shinhands.mu.Stationary.service.HistoryOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistoryOrderServiceImpl implements HistoryOrderService {
    @Autowired
    private HistoryOrderRepository historyOrderRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<HistoryOrderDTO> getAllOrderByCustomer(long customerId) {
        return historyOrderRepository.findAllByCustomer(customerId);
    }
}
