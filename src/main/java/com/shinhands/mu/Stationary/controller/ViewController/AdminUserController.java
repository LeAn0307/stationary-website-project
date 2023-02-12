package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.config.FileUploadUtil;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "pages")
public class AdminUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "user")
    public ModelMap getAllUser(ModelMap model) {
        List<UserDTO> users= userService.getAllUsers();
        users.sort(Comparator.comparing(userDTO1 -> userDTO1.getId()));
        model.addAttribute("users",users);
        return model;
    }
    @GetMapping(value = "user/{id}")
    public String getUserById(@PathVariable(name="id") long id,Model model) {
        UserDTO userDTO=userService.getUserById(id);
        model.addAttribute("user",userDTO);
        return "pages/edit-user";
    }
    @RequestMapping(path = "/user/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("photo") MultipartFile photo,UserDTO userDTO,Model model) {

            userService.addUser(userDTO);
        return "redirect:/pages/user";
    }
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public String updateProduct( UserDTO userDTO, Model model) {
                userService.updateUser(userDTO.getId(), userDTO);
        return "redirect:/pages/user";
    }
    @GetMapping("delete-user/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/pages/user";
    }

}
