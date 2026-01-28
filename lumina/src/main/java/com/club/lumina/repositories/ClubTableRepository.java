package com.club.lumina.repositories;

import com.club.lumina.models.ClubTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClubTableRepository extends JpaRepository<ClubTable, UUID> {
}
