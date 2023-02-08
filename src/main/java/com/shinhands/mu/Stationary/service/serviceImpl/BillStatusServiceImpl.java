package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.repository.BillStatusRepository;
import com.shinhands.mu.Stationary.service.BillStatusService;
import org.springframework.beans.factory.annotation.Autowired;

public class BillStatusServiceImpl implements BillStatusService {

    @Autowired
    private BillStatusRepository billStatusRepository;

    @Override
    public String getStatus(long id) {
        return billStatusRepository.getBillStatusByIdAndDeleted(id, 0L);
    }
}
