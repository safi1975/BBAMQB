package com.app.dataentry.controllers;

import com.app.dataentry.constants.Role;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.domain.UserDto;
import com.app.dataentry.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Secured(value = { Role.ADMIN })
    @GetMapping("/user")
    public String client(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user/index";
    }

    @Secured(value = { Role.ADMIN })
    @GetMapping("/user/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/form";
    }

    @Secured(value = { Role.ADMIN })
    @PostMapping("/user")
    public String save(Model model, @ModelAttribute(name = "user") UserDto userDto) {
        if (userService.saveUser(userDto) != null) {
            return "redirect:/user";
        }
        model.addAttribute("user", userDto);
        return "user/form";
    }
}
