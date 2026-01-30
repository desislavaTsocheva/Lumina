package com.club.lumina.services;

import com.club.lumina.dto.ClientRegisterDTO;
import com.club.lumina.models.Client;
import com.club.lumina.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public void addClient(@Valid ClientRegisterDTO clientDTO) {
        Client entity = modelMapper.map(clientDTO, Client.class);
        entity.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

        clientRepository.saveAndFlush(entity);
    }

    public void updateClient(ClientRegisterDTO dto) {
        Client existingClient = clientRepository.findById(dto.getId()).get();

        existingClient.setFirstName(dto.getFirstName());
        existingClient.setLastName(dto.getLastName());
        existingClient.setEmail(dto.getEmail());
        existingClient.setPhoneNumber(dto.getPhoneNumber());
        existingClient.setAge(dto.getAge());

        clientRepository.save(existingClient);
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

    public void uploadProfilePicture(String username, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty!");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            throw new IOException("File is too large! Max 2MB is allowed!");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("Only image files are allowed!");
        }

        Client client = clientRepository.findByUsername(username);
        String oldPhotoPath = client.getPhoto();

        String uploadDir = "src/main/resources/static/images/pfp/";
        Path uploadPath = Paths.get(uploadDir);

        if (oldPhotoPath != null && oldPhotoPath.startsWith("/images/pfp/")) {
            Path oldFilePath = Paths.get("src/main/resources/static" + oldPhotoPath);
            try {
                Files.deleteIfExists(oldFilePath);
                System.out.println("Deleted old photo: " + oldFilePath);
            } catch (IOException e) {
                System.err.println("Could not delete old file: " + e.getMessage());
            }
        }

        String fileName = username + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        client.setPhoto("/images/pfp/" + fileName);
        clientRepository.save(client);
    }

}
