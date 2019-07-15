package com.app.dataentry.domain;

import java.util.ArrayList;
import java.util.List;

public class ClientsDto {

    private List<ClientDto> clients = new ArrayList<>();

    public List<ClientDto> getClients() {
        return clients;
    }

    public void addClient(ClientDto clientDto) {
        this.clients.add(clientDto);
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }
}
