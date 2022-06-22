package com.app.dataentry.controllers;

import javax.validation.Valid;

import com.app.dataentry.constants.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dataentry.constants.Role;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.domain.ClientsDto;
import com.app.dataentry.services.ClientService;
import com.app.dataentry.services.SmsService;

@Controller
public class ClientController {

	@Autowired
	ClientService clientService;

	@Autowired
	SmsService smsService;

	@Secured(value = { Role.ADMIN })
	@GetMapping("/client")
	public String client(Model model) {
		model.addAttribute("clients", clientService.getClients());
		return "client/index";
	}

	@Secured(value = { Role.OPERATOR })
	@GetMapping("/client_oper")
	public String clientForOperator(Model model) {
		model.addAttribute("clients", clientService.getOperatorClients());
		return "client/index_oper";
	}

	@Secured(value = { Role.ADMIN, Role.OPERATOR })
	@GetMapping("/client/edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("client", clientService.getClient(id));
		return "client/form";
	}

	@Secured(value = { Role.ADMIN, Role.OPERATOR })
	@GetMapping("/client_multi")
	public String addMulti(Model model) {
		ClientsDto clients = new ClientsDto();
		for (int i = 0; i < 7; i++) {
			clients.addClient(clientService.getClient(0l));
		}
		model.addAttribute("clientsDto", clients);
		return "client/form_multi";
	}

	@Secured(value = { Role.ADMIN, Role.OPERATOR })
	@PostMapping("/client_multi")
	public String saveMulti(Model model, @Valid @ModelAttribute(name = "clientsDto") ClientsDto clientsDto,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("clientsDto", clientsDto);
			return "client/form_multi";
		}
		int count = 0;
		for (ClientDto clientDto : clientsDto.getClients()) {
			if (StringUtils.isEmpty(clientDto.getName())) {
				continue;
			}
			clientService.saveClient(clientDto);
			if (clientDto.getProduct().equals(Product.PRODUCT_3)) {
				count = count + 2;
			} else {
				count++;
			}
			smsService.sendClientRecordAddedSMS(clientDto.getMobileNo());
		}
		if (isOperator()) {
			redirectAttributes.addFlashAttribute("notyfyShow", true);
			redirectAttributes.addFlashAttribute("notyfyType", "success");
			redirectAttributes.addFlashAttribute("notyfyMsg", "Clients added: " + count);
			return "redirect:/";
		}
		return "redirect:/client";
	}

	@Secured(value = { Role.ADMIN, Role.OPERATOR })
	@PostMapping("/client")
	public String save(Model model, @Valid @ModelAttribute(name = "client") ClientDto clientDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("client", clientDto);
		if (result.hasErrors()) {
			return "client/form";
		}
		if (clientService.saveClient(clientDto) != null) {
			if (isOperator()) {
				redirectAttributes.addFlashAttribute("notyfyShow", true);
				redirectAttributes.addFlashAttribute("notyfyType", "success");
				if (clientDto.getId() == null) {
					if (clientDto.getProduct().equals(Product.PRODUCT_3)) {
						redirectAttributes.addFlashAttribute("notyfyMsg", "2 Clients added");
					} else {
						redirectAttributes.addFlashAttribute("notyfyMsg", "Client added");
					}
					smsService.sendClientRecordAddedSMS(clientDto.getMobileNo());

				} else {
					if (clientDto.getProduct().equals(Product.PRODUCT_3)) {
						redirectAttributes.addFlashAttribute("notyfyMsg", "2 Clients edited");
					} else {
						redirectAttributes.addFlashAttribute("notyfyMsg", "Client edited");
					}

				}

				return "redirect:/";
			}
			return "redirect:/client";
		}

		return "client/form";
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

	@Secured(value = { Role.ADMIN, Role.OPERATOR })
	@GetMapping("/client/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		clientService.deleteClient(id);
		redirectAttributes.addFlashAttribute("notyfyShow", true);
		redirectAttributes.addFlashAttribute("notyfyType", "success");
		redirectAttributes.addFlashAttribute("notyfyMsg", "Client removed");
		if (isOperator()) {
			return "redirect:/client_oper";
		} else {
			return "redirect:/client";
		}

	}
}
