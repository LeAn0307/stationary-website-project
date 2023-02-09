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
        return modelMapper.map(billRepository.findByDeleted(0L), new TypeToken<List<BillDTO>>(){}.getType());
    }

    @Override
    public BillDTO getBillsByBillId(long billId) {
        Bill bill = billRepository.findAllByDeletedAndId(0L, billId);
        if(bill != null) {
            return modelMapper.map(bill, BillDTO.class);
        }
        return null;
    }

    @Override
    public List<BillDTO> getBillsByOrderDate(Date dateOrder) {
        List<Bill> billList = billRepository.findAllByDateOrderAndDeleted(dateOrder, 0L);
        if(billList.isEmpty()) {
            return null;
        } else {
            return modelMapper.map(billList, new TypeToken<List<BillDTO>>(){}.getType());
        }
    }

    @Override
    public Long addBill(BillDTO billDTO) {
        return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class).getId();
    }

    @Override
    public BillDTO updateBill(long id, BillDTO billDTO)
    {
        Bill bill = billRepository.findAllByDeletedAndId(0L, id);
        if(bill == null) {
            return null;
        } else {
            return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class);
        }
    }

    @Override
    public long deleteBill(long id) {
        Bill deleteBill = billRepository.findAllByDeletedAndId(0L,id);
        deleteBill.setDeleted(1L);
        billRepository.save(deleteBill);
        return deleteBill.getId();
    }
}
