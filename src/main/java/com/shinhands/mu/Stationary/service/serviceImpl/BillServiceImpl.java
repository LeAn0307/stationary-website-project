package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.entity.Bill;
import com.shinhands.mu.Stationary.repository.BillRepository;
import com.shinhands.mu.Stationary.service.BillService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BillDTO> getAllBills() {
        return modelMapper.map(billRepository.findByDeleted((long)0), new TypeToken<List<BillDTO>>(){}.getType());
    }

    @Override
    public BillDTO getBillsByBillId(long billId) {
        return modelMapper.map(billRepository.findAllByDeletedAndId((long)0, billId), BillDTO.class);
    }

    @Override
    public List<BillDTO> getBillsByOrderDate(Date dateOrder) {
        return modelMapper.map(billRepository.findAllByDateOrderAndDeleted(dateOrder, (long)0), new TypeToken<List<BillDTO>>(){}.getType());
    }

    @Override
    public Long addBill(BillDTO billDTO) {
        return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class).getId();
    }

    @Override
    public BillDTO updateBill(long id, BillDTO billDTO)
    {
        Bill bill = billRepository.findAllByDeletedAndId((long)0, id);
        billDTO.setId(bill.getId());
        return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class);
    }

    @Override
    public long deleteBill(long id) {
        Bill deleteBill = billRepository.findAllByDeletedAndId((long)0,id);
        deleteBill.setDeleted((long)1);
        billRepository.save(deleteBill);
        return deleteBill.getId();
    }
}
