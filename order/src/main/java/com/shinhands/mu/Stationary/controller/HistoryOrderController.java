package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.service.HistoryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/historyOrder")
public class HistoryOrderController {

    @Autowired
    private HistoryOrderService historyOrderService;

    @GetMapping("")
    public ResponseEntity<List<HistoryOrderDTO>> findAllBillForCustomer(@RequestParam() long id) {
        List<HistoryOrderDTO> billList = historyOrderService.getAllOrderByCustomer(id);
        if(billList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok().body(billList);
        }
    }
}
