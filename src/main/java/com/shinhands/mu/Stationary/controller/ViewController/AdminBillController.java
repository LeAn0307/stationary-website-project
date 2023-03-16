package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.BillResponseDTO;
import com.shinhands.mu.Stationary.dto.BillStatusDTO;
import com.shinhands.mu.Stationary.service.BillService;
import com.shinhands.mu.Stationary.service.BillStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminBillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillStatusService billStatusService;

    @GetMapping( value ="bill")
    public ModelMap getAllBill() {
        List<BillResponseDTO> billDTOList = billService.getAllBills();
        List<BillStatusDTO> billStatusDTOList = billStatusService.getAllBillStatus();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("billList", billDTOList);
        modelMap.addAttribute("billStatusList", billStatusDTOList);
        return modelMap;
    }

    @GetMapping("bill/updateStatus")
    public String updateStatusBill(@RequestParam long id, @RequestParam long statusId, Model model) {
        billService.updateStatusBill(id, statusId);
        return "redirect:/admin/bill";
    }

//    @PostMapping("bill/update/payment/{id}")
//    public String updatePaymentBill(@PathVariable long id, Model model) {
//        billService.updateStatusBill(id, 2L);
//        return "redirect:/admin/bill";
//    }

    @GetMapping("bill/delete/{id}")
    public String deleteBill(@PathVariable long id, Model model) {
        billService.deleteBill(id);
        return "redirect:/admin/bill";
    }
}
