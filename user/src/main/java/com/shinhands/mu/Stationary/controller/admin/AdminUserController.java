package com.shinhands.mu.Stationary.controller.admin;

import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminUserController {
    @Autowired
    private UserWebService userService;

    @GetMapping(value = "user")
    public ModelMap getAllUser(ModelMap model) {
        List<UserDTO> users= userService.getAllUsers();
        users.sort(Comparator.comparing(userDTO1 -> userDTO1.getId()));
        model.addAttribute("users",users);
        return model;
    }
    @GetMapping(value = "user/{id}")
    public String getUserById(@PathVariable(name="id") long id, Model model) {
        UserDTO userDTO=userService.getUserById(id);
        model.addAttribute("user",userDTO);
        return "admin/edit-user";
    }
    @RequestMapping(path = "/user/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("photo") MultipartFile photo, UserDTO userDTO, Model model) {

        userService.addUser(userDTO);
        return "redirect:/admin/user";
    }
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public String updateProduct( UserDTO userDTO, Model model) {
        userService.updateUser(userDTO.getId(), userDTO);
        return "redirect:/admin/user";
    }
    @GetMapping("delete-user/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
