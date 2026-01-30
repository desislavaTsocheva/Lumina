package com.club.lumina.repositories;

import com.club.lumina.models.Client;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByUsername(String username);
    Client findByEmail (String email);
}
