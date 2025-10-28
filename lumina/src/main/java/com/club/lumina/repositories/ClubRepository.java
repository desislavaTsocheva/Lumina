package com.club.lumina.repositories;

import com.club.lumina.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClubRepository  extends JpaRepository<Club, UUID> {
}
