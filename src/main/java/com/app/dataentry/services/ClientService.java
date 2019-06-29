package com.app.dataentry.services;

import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.model.Client;

import java.util.List;

public interface ClientService {

	List<ClientDto> getClients();

	Client saveClient(ClientDto clientDto);

	boolean deleteClient(Long id);

	ClientDto getClient(Long id);
}
