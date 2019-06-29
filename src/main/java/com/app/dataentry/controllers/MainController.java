package com.app.dataentry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    StatusService statusService; 

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

}
