package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String newPage(Model model) {
        model.addAttribute("name", "John Doe");
        model.addAttribute("loginFailed", false);
        return "admin/login";
    }

    @RequestMapping(path = "/login/checkAdmin", method = RequestMethod.POST)
    public String checkAdmin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if(userService.checkAdmin(email, password)) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("loginFailed", true);
            return "admin/login";
        }
    }
}
