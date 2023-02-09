package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.entity.BillStatus;
import com.shinhands.mu.Stationary.repository.BillStatusRepository;
import com.shinhands.mu.Stationary.service.BillStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillStatusServiceImpl implements BillStatusService {

    @Autowired
    private BillStatusRepository billStatusRepository;

    @Override
    public String getStatus(long id) {
        BillStatus billStatus = billStatusRepository.findByIdAndDeleted(id, 0L);
        if(billStatus != null) {
            return billStatus.getStatus();
        } else {
            return "";
        }
    }
}
