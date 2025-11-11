package com.club.lumina.services;

import com.club.lumina.models.Client;
import com.club.lumina.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public void addClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.saveAndFlush(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    public boolean isUsernameAvailable(String username) {
        return clientRepository.findByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        return clientRepository.findByEmail(email)==null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if(client == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        return client;
    }
}
