package com.club.lumina.repositories;

import com.club.lumina.models.Client;
import com.club.lumina.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClubRepository  extends JpaRepository<Club, UUID> {
}
