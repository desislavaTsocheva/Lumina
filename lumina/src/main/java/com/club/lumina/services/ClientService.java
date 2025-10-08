package com.club.lumina.services;

import com.club.lumina.models.Client;
import com.club.lumina.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public void addClient(Client client) {
        clientRepository.saveAndFlush(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
