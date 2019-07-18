package com.app.dataentry.services;

import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.model.Client;
import com.app.dataentry.report.PageList;

import java.util.List;

public interface ClientService {

	List<ClientDto> getClients();

	Client saveClient(ClientDto clientDto);

	boolean deleteClient(Long id);

	ClientDto getClient(Long id);
	
	PageList getPages();

	long groupsCount();

	long operatorCount();

	List<ClientDto> getOperatorClients();
}
