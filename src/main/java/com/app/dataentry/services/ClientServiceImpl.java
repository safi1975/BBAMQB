package com.app.dataentry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.model.Client;
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
	public ClientDto getClient(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return new ClientDto(client.orElse(new Client()));
	}
}
