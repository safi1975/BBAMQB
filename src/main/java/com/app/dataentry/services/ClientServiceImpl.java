package com.app.dataentry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dataentry.domain.ClientDto;
import com.app.dataentry.model.Client;
import com.app.dataentry.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;

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
}
