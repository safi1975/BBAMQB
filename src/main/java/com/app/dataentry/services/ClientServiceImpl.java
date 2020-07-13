package com.app.dataentry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
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
    @Transactional(readOnly = true)
    public List<ClientDto> getOperatorClients() {
        List<ClientDto> clients = new ArrayList<>();
        for (Client c : clientRepository.findAllByCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName())) {
            clients.add(new ClientDto(c));
        }
        return clients;
    }

    LinkedList<Client> clientsToPage = new LinkedList<>();
    int low = 0;
    int high = 0;

    @Override
    @Transactional
    public Client saveClient(ClientDto clientDto) {
        Client entity = clientDto.mapToEntity();
        if (!StringUtils.isEmpty(clientDto.getId())) {
            Optional<Client> clientFromDb = clientRepository.findById(clientDto.getId());
            if (clientFromDb.isPresent()) {
                entity.setCreatedBy(clientFromDb.get().getCreatedBy());
                entity.setCreatedAt(clientFromDb.get().getCreatedAt());
            }
        }
        return clientRepository.save(entity);
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

    private Page generatePage() {
        Page page = new Page();

        String phone1 = "";
        for (int i = 0; 7 > i; i++) {
            if (clientsToPage.isEmpty()) {
                break;
            }
            Client client = clientsToPage.getFirst();
            phone1 = phone1 + (StringUtils.isEmpty(client.getMobileNo()) ? "" : client.getMobileNo() + ",");
            switch (i) {
                case 0:
                    page.setClient1prod1(client.getName());
                    break;
                case 1:
                    page.setClient2prod1(client.getName());
                    break;
                case 2:
                    page.setClient3prod1(client.getName());
                    break;
                case 3:
                    page.setClient4prod1(client.getName());
                    break;
                case 4:
                    page.setClient5prod1(client.getName());
                    break;
                case 5:
                    page.setClient6prod1(client.getName());
                    break;
                case 6:
                    page.setClient7prod1(client.getName());
                    break;
            }
            clientsToPage.removeFirst();
        }
        page.setPhone1(phone1);

        String phone2 = "";
        for (int i = 0; 7 > i; i++) {
            if (clientsToPage.isEmpty()) {
                break;
            }
            Client client = clientsToPage.getFirst();
            phone2 = phone2 + (StringUtils.isEmpty(client.getMobileNo()) ? "" : client.getMobileNo() + ",");
            switch (i) {
                case 0:
                    page.setClient1prod2(client.getName());
                    break;
                case 1:
                    page.setClient2prod2(client.getName());
                    break;
                case 2:
                    page.setClient3prod2(client.getName());
                    break;
                case 3:
                    page.setClient4prod2(client.getName());
                    break;
                case 4:
                    page.setClient5prod2(client.getName());
                    break;
                case 5:
                    page.setClient6prod2(client.getName());
                    break;
                case 6:
                    page.setClient7prod2(client.getName());
                    break;
            }
            clientsToPage.removeFirst();
        }
        page.setPhone2(phone2);

        String phone3 = "";
        for (int i = 0; 7 > i; i++) {
            if (clientsToPage.isEmpty()) {
                break;
            }
            Client client = clientsToPage.getFirst();
            phone3 = phone3 + (StringUtils.isEmpty(client.getMobileNo()) ? "" : client.getMobileNo() + ",");
            switch (i) {
                case 0:
                    page.setClient1prod3(client.getName());
                    break;
                case 1:
                    page.setClient2prod3(client.getName());
                    break;
                case 2:
                    page.setClient3prod3(client.getName());
                    break;
                case 3:
                    page.setClient4prod3(client.getName());
                    break;
                case 4:
                    page.setClient5prod3(client.getName());
                    break;
                case 5:
                    page.setClient6prod3(client.getName());
                    break;
                case 6:
                    page.setClient7prod3(client.getName());
                    break;
            }
            clientsToPage.removeFirst();
        }
        page.setPhone3(phone3);

        String phone4 = "";
        for (int i = 0; 7 > i; i++) {
            if (clientsToPage.isEmpty()) {
                break;
            }
            Client client = clientsToPage.getFirst();
            phone4 = phone4 + (StringUtils.isEmpty(client.getMobileNo()) ? "" : client.getMobileNo() + ",");
            switch (i) {
                case 0:
                    page.setClient1prod4(client.getName());
                    break;
                case 1:
                    page.setClient2prod4(client.getName());
                    break;
                case 2:
                    page.setClient3prod4(client.getName());
                    break;
                case 3:
                    page.setClient4prod4(client.getName());
                    break;
                case 4:
                    page.setClient5prod4(client.getName());
                    break;
                case 5:
                    page.setClient6prod4(client.getName());
                    break;
                case 6:
                    page.setClient7prod4(client.getName());
                    break;
            }
            clientsToPage.removeFirst();
        }
        page.setPhone4(phone4);
        page.setNos("No: " + low + "-" + high);

        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList getPages() {
        PageList pages = new PageList();
        low = -3;
        high = 0;

        for (Client client : clientRepository.findAll()) {
            if (client.getProduct().equals(Product.PRODUCT_3)) {
                clientsToPage.add(client);
                clientsToPage.add(client);
            } else {
                clientsToPage.add(client);
            }
            if (clientsToPage.size() >= 28) {
                low = low + 4;
                high = high + 4;
                pages.addPage(generatePage());
            }
        }
        low = high + 1;
        high = low + (clientsToPage.size() / 7);
        pages.addPage(generatePage());
        return pages;
    }

    @Override
    public long groupsCount() {
        long sum = clientRepository.count() + clientRepository.findAllByProduct(Product.PRODUCT_3).size();
        long count = sum / 7;
        if (sum % 7 != 0) {
            count++;
        }
        return count;
    }

    @Override
    public long operatorCount() {
        Long allProduct = clientRepository.countByCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        Long additonal = clientRepository.countByCreatedByAndProduct(SecurityContextHolder.getContext().getAuthentication().getName(), Product.PRODUCT_3);
        return allProduct + additonal;

    }
}
