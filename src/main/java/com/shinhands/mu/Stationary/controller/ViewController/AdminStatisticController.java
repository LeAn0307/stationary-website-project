package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.service.StatisticService;
import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "pages")
public class AdminStatisticController {

    @Autowired
    StatisticService statisticService;
    @Autowired
    UserService userService;
    @GetMapping(value = "statistic")
    public ModelMap getAll(ModelMap model) {

        model.addAttribute("totalBill",statisticService.countTotalBill());
        model.addAttribute("countBill", statisticService.countBill());
        model.addAttribute("countOrder", statisticService.countOrder());
        model.addAttribute("countUser", statisticService.countUser());
        List<UserDTO> users= userService.getAllUsers();
        users.sort(Comparator.comparing(userDTO1 -> userDTO1.getId()));
        model.addAttribute("users",users);
        return model;
    }
}
