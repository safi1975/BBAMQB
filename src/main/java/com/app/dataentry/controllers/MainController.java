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
import com.app.dataentry.services.ReportServiceImpl;

@Controller
public class MainController {

	@Autowired
	ReportServiceImpl re;
	
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
    	ByteArrayOutputStream output = re.generateReport();
    	return output.toByteArray();
    }
}
