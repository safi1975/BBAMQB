package com.app.dataentry.controllers;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dataentry.constants.Role;
import com.app.dataentry.domain.UserDto;
import com.app.dataentry.model.User;
import com.app.dataentry.services.ClientService;
import com.app.dataentry.services.ReportService;
import com.app.dataentry.services.SmsService;
import com.app.dataentry.services.UserService;

@Controller
public class MainController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	SmsService smsService;
	
    @GetMapping("/")
    public String main(Model model) {
        if (isOperator()) {
            model.addAttribute("count", "Clients entered by you: " + clientService.operatorCount());
        } else {
            model.addAttribute("count", "Groups in system: " + clientService.groupsCount());
        }
       return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value="error",required=false) boolean hasError) {
    	if (hasError) {
    		model.addAttribute("errorMsg", "Login failed");
    	}
        return "login_page";
    }
    
    @Secured(value = { Role.ADMIN})
    @ResponseBody
    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] report(Model model, HttpServletResponse response) {
    	ByteArrayOutputStream output = reportService.generateReport(clientService.getPages());
    	return output.toByteArray();
    }
    
    @Secured(value = { Role.ADMIN})
    @ResponseBody
    @GetMapping(value = "/report_csv", produces = "text/csv")
    public byte[] reportCSV(Model model, HttpServletResponse response) {
    	response.addHeader("Content-Disposition", "attachment; filename=report.csv");
    	return reportService.generateReportCSV(clientService.getPages());
    }

    private boolean isOperator() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga.getAuthority().equals(Role.OPERATOR)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @ResponseBody
    @PostMapping(value="/smscode", produces= MediaType.TEXT_PLAIN_VALUE)
    public String smscode(@ModelAttribute UserDto userDto) {
        User user = userService.getUserByName(userDto.getName());
    	
    	if (user == null || !bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
    		return "Bad login/password";
    	}

    	String code = smsService.generateCode();
    	user.setCode(code);
    	userService.saveUser(user);
    	
    	return smsService.sendSms(user.getMobileNo(), code);
    }   
}
