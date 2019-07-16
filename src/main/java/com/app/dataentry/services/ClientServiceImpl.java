package com.app.dataentry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.dataentry.constants.Product;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.model.Client;
import com.app.dataentry.report.Page;
import com.app.dataentry.report.PageList;
import com.app.dataentry.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ClientDto> getClients() {
		List<ClientDto> clients = new ArrayList<>();
		for (Client c : clientRepository.findAll()) {
			clients.add(new ClientDto(c));
		}
		return clients;
	}

	@Override
	@Transactional
	public Client saveClient(ClientDto clientDto) {
		return clientRepository.save(clientDto.mapToEntity());
	}

	@Override
	@Transactional
	public boolean deleteClient(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			clientRepository.delete(client.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ClientDto getClient(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return new ClientDto(client.orElse(new Client()));
	}

	@Override
	@Transactional(readOnly = true)
	public PageList getPages() {
		PageList pages = new PageList();
		List<Client> products1 = clientRepository.findAllByProduct(Product.PRODUCT_1);
		List<Client> products2 = clientRepository.findAllByProduct(Product.PRODUCT_2);
		List<Client> products3 = clientRepository.findAllByProduct(Product.PRODUCT_3);

		int size;
		if (products1.size() >= products2.size() && products1.size() >= products3.size()) {
			size = products1.size();
		} else if (products2.size() >= products3.size()) {
			size = products2.size();
		} else {
			size = products3.size();
		}

		for (int i = 6; size + 7 > i; i = i + 7) {
			Page page = new Page();

			String phone1 = "";
			try {
				Client client1 = products1.get(i - 6);
				phone1 = phone1 + (StringUtils.isEmpty(client1.getMobileNo()) ? "" : client1.getMobileNo() + " ");
				page.setClient1prod1(client1.getName());
				Client client2 = products1.get(i - 5);
				phone1 = phone1 + (StringUtils.isEmpty(client2.getMobileNo()) ? "" : client2.getMobileNo() + " ");
				page.setClient2prod1(client2.getName());
				Client client3 = products1.get(i - 4);
				phone1 = phone1 + (StringUtils.isEmpty(client3.getMobileNo()) ? "" : client3.getMobileNo() + " ");
				page.setClient3prod1(client3.getName());
				Client client4 = products1.get(i - 3);
				phone1 = phone1 + (StringUtils.isEmpty(client4.getMobileNo()) ? "" : client4.getMobileNo() + " ");
				page.setClient4prod1(client4.getName());
				Client client5 = products1.get(i - 2);
				phone1 = phone1 + (StringUtils.isEmpty(client5.getMobileNo()) ? "" : client5.getMobileNo() + " ");
				page.setClient5prod1(client5.getName());
				Client client6 = products1.get(i - 1);
				phone1 = phone1 + (StringUtils.isEmpty(client6.getMobileNo()) ? "" : client6.getMobileNo() + " ");
				page.setClient6prod1(client6.getName());
				Client client7 = products1.get(i);
				phone1 = phone1 + (StringUtils.isEmpty(client7.getMobileNo()) ? "" : client7.getMobileNo() + " ");
				page.setClient7prod1(client7.getName());
			} catch (IndexOutOfBoundsException e) {
			}
			page.setPhone1(phone1);

			String phone2 = "";
			try {
				Client client1 = products2.get(i - 6);
				phone2 = phone2 + (StringUtils.isEmpty(client1.getMobileNo()) ? "" : client1.getMobileNo() + " ");
				page.setClient1prod2(client1.getName());
				Client client2 = products2.get(i - 5);
				phone2 = phone2 + (StringUtils.isEmpty(client2.getMobileNo()) ? "" : client2.getMobileNo() + " ");
				page.setClient2prod2(client2.getName());
				Client client3 = products2.get(i - 4);
				phone2 = phone2 + (StringUtils.isEmpty(client3.getMobileNo()) ? "" : client3.getMobileNo() + " ");
				page.setClient3prod2(client3.getName());
				Client client4 = products2.get(i - 3);
				phone2 = phone2 + (StringUtils.isEmpty(client4.getMobileNo()) ? "" : client4.getMobileNo() + " ");
				page.setClient4prod2(client4.getName());
				Client client5 = products2.get(i - 2);
				phone2 = phone2 + (StringUtils.isEmpty(client5.getMobileNo()) ? "" : client5.getMobileNo() + " ");
				page.setClient5prod2(client5.getName());
				Client client6 = products2.get(i - 1);
				phone2 = phone2 + (StringUtils.isEmpty(client6.getMobileNo()) ? "" : client6.getMobileNo() + " ");
				page.setClient6prod2(client6.getName());
				Client client7 = products2.get(i);
				phone2 = phone2 + (StringUtils.isEmpty(client7.getMobileNo()) ? "" : client7.getMobileNo() + " ");
				page.setClient7prod2(client7.getName());
			} catch (IndexOutOfBoundsException e) {
			}
			page.setPhone2(phone2);

			String phone3 = "";
			try {
				Client client1 = products3.get(i - 6);
				phone3 = phone3 + (StringUtils.isEmpty(client1.getMobileNo()) ? "" : client1.getMobileNo() + " ");
				page.setClient1prod3(client1.getName());
				Client client2 = products3.get(i - 5);
				phone3 = phone3 + (StringUtils.isEmpty(client2.getMobileNo()) ? "" : client2.getMobileNo() + " ");
				page.setClient2prod3(client2.getName());
				Client client3 = products3.get(i - 4);
				phone3 = phone3 + (StringUtils.isEmpty(client3.getMobileNo()) ? "" : client3.getMobileNo() + " ");
				page.setClient3prod3(client3.getName());
				Client client4 = products3.get(i - 3);
				phone3 = phone3 + (StringUtils.isEmpty(client4.getMobileNo()) ? "" : client4.getMobileNo() + " ");
				page.setClient4prod3(client4.getName());
				Client client5 = products3.get(i - 2);
				phone3 = phone3 + (StringUtils.isEmpty(client5.getMobileNo()) ? "" : client5.getMobileNo() + " ");
				page.setClient5prod3(client5.getName());
				Client client6 = products3.get(i - 1);
				phone3 = phone3 + (StringUtils.isEmpty(client6.getMobileNo()) ? "" : client6.getMobileNo() + " ");
				page.setClient6prod3(client6.getName());
				Client client7 = products3.get(i);
				phone3 = phone3 + (StringUtils.isEmpty(client7.getMobileNo()) ? "" : client7.getMobileNo() + " ");
				page.setClient7prod3(client7.getName());
			} catch (IndexOutOfBoundsException e) {
			}
			page.setPhone3(phone3);
			page.setPhone4(phone3);

			pages.addPage(page);
		}
		return pages;
	}
}
