package com.club.lumina.repositories;

import com.club.lumina.models.Client;
import com.club.lumina.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository  extends JpaRepository<Reservation, UUID> {
}
