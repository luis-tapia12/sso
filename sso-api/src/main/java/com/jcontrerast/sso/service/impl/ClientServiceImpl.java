package com.jcontrerast.sso.service.impl;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Client;
import com.jcontrerast.sso.repository.ClientRepository;
import com.jcontrerast.sso.service.ClientService;
import com.jcontrerast.sso.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<Client> getClients(PageDTO pageDTO) {
        return repository.findAll(Utils.getPageable(pageDTO));
    }

    @Override
    public Client getClient(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The client with id " + id + " does not exist"));
    }

    @Override
    public Client saveClient(Client client) {
        client.generateId();
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return repository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        Client currentClient = getClient(client.getId());
        Utils.copyNonNull(client, currentClient);
        if (client.getPassword() != null) {
            currentClient.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return repository.save(currentClient);
    }

    @Override
    public void deleteClient(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("The client with id " + id + " does not exist");
        }
    }
}
