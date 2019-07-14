package com.app.dataentry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dataentry.constants.Role;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.services.ClientService;

@Controller
public class ClientController {

	@Autowired
	ClientService clientService;

	@Secured(value = { Role.ADMIN })
	@GetMapping("/client")
	public String client(Model model) {
		model.addAttribute("clients", clientService.getClients());
		return "client/index";
	}

	@Secured(value = { Role.ADMIN })
	@GetMapping("/client/edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("client", clientService.getClient(id));
		return "client/form";
	}
	
	@Secured(value = { Role.ADMIN })
	@PostMapping("/client")
	public String save(Model model, @ModelAttribute(name = "client") ClientDto clientDto) {
		if (clientService.saveClient(clientDto) != null) {
			return "redirect:/client";
		}
		model.addAttribute("client", clientDto);
		return "client/form";
	}
}
