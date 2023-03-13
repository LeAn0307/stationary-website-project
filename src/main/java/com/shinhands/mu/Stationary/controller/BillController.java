package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.BillResponseDTO;
import com.shinhands.mu.Stationary.service.BillDetailService;
import com.shinhands.mu.Stationary.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("")
    public ResponseEntity getAllBills() {
        try {
            List<BillResponseDTO> billDTO = billService.getAllBills();

            if(billDTO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                billDTO.stream().forEach(i -> {
                    i.setBillDetailList(billDetailService.getByIdBill(i.getId()));
                });
                return new ResponseEntity<>(billDTO ,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getBillById(@PathVariable Long id) {
//        try {
            BillResponseDTO billResponse = billService.getById(id);
            if(billResponse == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                billResponse.setBillDetailList(billDetailService.getByIdBill(billResponse.getId()));
                return new ResponseEntity<>(billResponse ,HttpStatus.OK);
            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
//
//    @GetMapping("/byDate")
//    public ResponseEntity<List<BillDTO>> getBillByDateOrder(@RequestParam String orderDate) {
//        try {
//            Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
//            List<BillDTO> billDTO = billService.getBillsByOrderDate(newDate);
//            if(billDTO.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
//                billDTO.stream().forEach(i -> i.setStatus(billStatusService.getStatus(i.getIdBillStatus())));
//                return new ResponseEntity<>(billDTO ,HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @PostMapping("")
    public ResponseEntity createBill(@RequestBody BillDTO billDTO) {
        try {
            BillDTO billDTO1 = billService.addBill(billDTO);
            if(billDTO1 != null){
                return new ResponseEntity<>(billDTO1 ,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillDTO> updateBill(@PathVariable long id, @RequestBody BillDTO billDTO) {
        try {
            return new ResponseEntity<>(billService.updateBill(id ,billDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBill(@PathVariable long id) {
       try {
           long result = billService.deleteBill(id);
           return ResponseEntity.ok().body(result);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
