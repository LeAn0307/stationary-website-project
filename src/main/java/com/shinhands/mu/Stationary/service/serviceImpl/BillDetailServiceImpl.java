package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillDetailDTO;
import com.shinhands.mu.Stationary.entity.BillDetail;
import com.shinhands.mu.Stationary.repository.BillDetailRepository;
import com.shinhands.mu.Stationary.service.BillDetailService;
import com.shinhands.mu.Stationary.service.BillService;
import org.modelmapper.ModelMapper;
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
    public Boolean addBillDetail(long billId ,List<BillDetailDTO> billDetailDTOList) {
        try {
            List<BillDetailDTO> newBillDetailListDTO = new ArrayList<>();
            for(final BillDetailDTO billDetailDTO : billDetailDTOList) {
                billDetailDTO.setIdBill(billId);
                billDetailDTO.setCreatedAt(Date.valueOf(LocalDate.now()));
                billDetailDTO.setUpdatedAt(Date.valueOf(LocalDate.now()));
                billDetailRepository.save(modelMapper.map(billDetailDTO, BillDetail.class));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BillDetailDTO updateBillDetail(long id, BillDetailDTO billDetailDTO) {
        BillDetail billDetail = billDetailRepository.findBillDetailByIdAndDeleted(id, 0);
        billDetailDTO.setId(billDetail.getId());
        return modelMapper.map(billDetailRepository.save(modelMapper.map(billDetailDTO, BillDetail.class)), BillDetailDTO.class);
    }
}
