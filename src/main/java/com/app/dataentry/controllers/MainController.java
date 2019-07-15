package com.app.dataentry.controllers;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dataentry.constants.Role;
import com.app.dataentry.services.ClientService;
import com.app.dataentry.services.ReportService;

@Controller
public class MainController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	ClientService clientService;
	
    @GetMapping("/")
    public String main(Model model) {
       return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login_page";
    }
    
    @Secured(value = { Role.ADMIN, Role.OPERATOR })
    @ResponseBody
    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] report(Model model, HttpServletResponse response) {
    	ByteArrayOutputStream output = reportService.generateReport(clientService.getPages());
    	return output.toByteArray();
    }
}
