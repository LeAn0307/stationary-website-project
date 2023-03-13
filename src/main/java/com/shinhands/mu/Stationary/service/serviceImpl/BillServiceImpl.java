package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillResponseDTO;
import com.shinhands.mu.Stationary.entity.Bill;
import com.shinhands.mu.Stationary.repository.BillRepository;
import com.shinhands.mu.Stationary.repository.BillRepositoryMybatis;
import com.shinhands.mu.Stationary.service.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillRepositoryMybatis billRepositoryMybatis;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BillResponseDTO> getAllBills() {
        return billRepositoryMybatis.findAll();
    }

    @Override
    public BillResponseDTO getById(Long id) {
        return billRepositoryMybatis.findById(id);
    }

    //    @Override
//    public BillDTO getBillsByBillId(long billId) {
//        Bill bill = billRepository.findByIdEqualsAndDeletedEquals(billId, 0L);
//        if(bill != null) {
//            return modelMapper.map(bill, BillDTO.class);
//        }
//        return null;
//    }
//
//    @Override
//    public List<BillDTO> getBillsByOrderDate(Date dateOrder) {
//        List<Bill> billList = billRepository.findAllByDateOrderAndDeleted(dateOrder, 0L);
//        if(billList.isEmpty()) {
//            return null;
//        } else {
//            return modelMapper.map(billList, new TypeToken<List<BillDTO>>(){}.getType());
//        }
//    }
//
    @Override
    public BillDTO addBill(BillDTO billDTO) {
        return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class);
    }

    @Override
    public BillDTO updateBill(long id, BillDTO billDTO)
    {
        Bill bill = billRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if(bill == null) {
            return null;
        } else {
            return modelMapper.map(billRepository.save(modelMapper.map(billDTO, Bill.class)), BillDTO.class);
        }
    }

    @Override
    public long deleteBill(long id) {
        Bill deleteBill = billRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        deleteBill.setDeleted(1L);
        billRepository.save(deleteBill);
        return deleteBill.getId();
    }

    @Override
    public boolean updateStatusBill(long id, long statusId) {
        Bill bill = billRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        bill.setIdBillStatus(statusId);
        billRepository.save(bill);
        return true;
    }
}
