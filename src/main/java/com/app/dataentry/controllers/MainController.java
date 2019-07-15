package com.app.dataentry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dataentry.services.ReportServiceImpl;

@Controller
public class MainController {

	@Autowired
	ReportServiceImpl re;
	
    @GetMapping("/")
    public String main(Model model) {
    	re.generateReport();
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login_page";
    }

}
