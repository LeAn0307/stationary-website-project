package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "pages")
public class UserElements {

    @GetMapping(value = "user-elements")
    public ModelMap CategoryElements(ModelMap model) {

        UserDTO[] users= new UserDTO[]{new UserDTO(1L,"1","img.url",1234L,1L),new UserDTO(1L,"1","img.url",1234L,1L),new UserDTO(1L,"1","img.url",1234L,1L)};
        model.addAttribute("users",users);
        return model;
    }

}
