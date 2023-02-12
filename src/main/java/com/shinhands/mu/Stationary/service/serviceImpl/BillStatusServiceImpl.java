package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillStatusDTO;
import com.shinhands.mu.Stationary.entity.BillStatus;
import com.shinhands.mu.Stationary.repository.BillStatusRepository;
import com.shinhands.mu.Stationary.service.BillStatusService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillStatusServiceImpl implements BillStatusService {

    @Autowired
    private BillStatusRepository billStatusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String getStatus(long id) {
        BillStatus billStatus = billStatusRepository.findByIdAndDeleted(id, 0L);
        if(billStatus != null) {
            return billStatus.getStatus();
        } else {
            return "";
        }
    }

    @Override
    public List<BillStatusDTO> getAllBillStatus() {
        return modelMapper.map(billStatusRepository.findAllByDeleted(0L), new TypeToken<List<BillDTO>>(){}.getType());
    }
}
