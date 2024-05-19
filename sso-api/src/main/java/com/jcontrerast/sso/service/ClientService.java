package com.jcontrerast.sso.service;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Client;
import org.springframework.data.domain.Page;

public interface ClientService {
    Page<Client> getClients(PageDTO pageDTO);

    Client getClient(String id);

    Client saveClient(Client client);

    Client updateClient(Client client);

    void deleteClient(String id);
}
