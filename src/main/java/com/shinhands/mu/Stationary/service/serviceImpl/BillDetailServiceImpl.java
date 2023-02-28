package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillDetailDTO;
import com.shinhands.mu.Stationary.dto.CartCouponDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.entity.BillDetail;
import com.shinhands.mu.Stationary.repository.BillDetailRepository;
import com.shinhands.mu.Stationary.service.BillDetailService;
import com.shinhands.mu.Stationary.service.BillService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BillDetailDTO addBillDetail(BillDetailDTO billDetailDTO) {
        return modelMapper.map(billDetailRepository.save(modelMapper.map(billDetailDTO, BillDetail.class)), BillDetailDTO.class);
    }

    @Override
    public BillDetailDTO updateBillDetail(long id, BillDetailDTO billDetailDTO) {
        BillDetail billDetail = billDetailRepository.findBillDetailByIdAndDeleted(id, 0L);
        if(billDetail == null) {
            return null;
        } else {
            return modelMapper.map(billDetailRepository.save(modelMapper.map(billDetailDTO, BillDetail.class)), BillDetailDTO.class);
        }
    }

    @Override
    public List<BillDetailDTO> getByIdBill(Long idBill) {
        return modelMapper.map(billDetailRepository.findByIdBillAndDeleted(idBill, 0L), new TypeToken<List<BillDetailDTO>>() {
        }.getType());
    }
}
