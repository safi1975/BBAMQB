package com.app.dataentry.services;

import com.app.dataentry.domain.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getClients();
}
