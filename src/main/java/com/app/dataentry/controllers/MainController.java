package com.app.dataentry.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login_page";
    }

}
