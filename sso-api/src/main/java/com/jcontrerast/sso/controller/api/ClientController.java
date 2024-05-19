package com.jcontrerast.sso.controller.api;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Client;
import com.jcontrerast.sso.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Client> getClients(PageDTO pageDTO) {
        return service.getClients(pageDTO);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable String id) {
        return service.getClient(id);
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return service.saveClient(client);
    }

    @PatchMapping("/{id}")
    public Client updateClient(@PathVariable String id, @RequestBody Client client) {
        client.setId(id);
        return service.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        service.deleteClient(id);
    }
}
