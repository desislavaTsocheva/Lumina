package com.club.lumina.repositories;

import com.club.lumina.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("SELECT r.clubTable.id FROM Reservation r WHERE r.clubTable.club.id = :clubId AND r.date = :time")
    List<UUID> findOccupiedTableIdsByClubAndId(UUID clubId, LocalDateTime time);

    boolean existsByClubTable_IdAndDate(UUID tableId, LocalDateTime time);
}