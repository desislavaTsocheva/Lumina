package com.club.lumina.services;

import com.club.lumina.dto.ReservationDTO;
import com.club.lumina.models.Client;
import com.club.lumina.models.ClubTable;
import com.club.lumina.models.Reservation;
import com.club.lumina.repositories.ClientRepository;
import com.club.lumina.repositories.ClubRepository;
import com.club.lumina.repositories.ClubTableRepository;
import com.club.lumina.repositories.ReservationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final ClubRepository clubRepository;
    private final ClubTableRepository tableRepository;

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, ClubRepository clubRepository, ClubTableRepository tableRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.clubRepository = clubRepository;
        this.tableRepository = tableRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.saveAndFlush(reservation);
    }

    public void update(ReservationDTO dto) {
        Reservation reservation;

        if (dto.getId() != null) {
            reservation = reservationRepository.findById(dto.getId()).orElse(new Reservation());
            reservation.setId(dto.getId());
        } else {
            reservation = new Reservation();
        }

        reservation.setDate(dto.getDate());
        reservation.setCount(dto.getCount());
        reservation.setType(dto.getType());
        reservation.setDiscount(dto.getDiscount());
        reservation.setStatus("Confirmed");

        reservation.setClient(clientRepository.findById(dto.getClients_id())
                .orElseThrow(() -> new RuntimeException("Клиентът не е намерен")));

        reservation.setClub(clubRepository.findById(dto.getClub_id())
                .orElseThrow(() -> new RuntimeException("Клубът не е намерен")));

        reservation.setClubTable(tableRepository.findById(dto.getTable_id())
                .orElseThrow(() -> new RuntimeException("Масата не е намерена")));

        reservationRepository.save(reservation);
    }

    public Reservation findById(UUID id) {
       Optional<Reservation> reservation= reservationRepository.findById(id);
        return reservation.orElse(null);
    }

    public void delete(UUID id) {
        reservationRepository.deleteById(id);
    }

    // В ReservationService.java
    public List<UUID> getOccupiedTableIds(UUID clubId, LocalDateTime time) {
        List<UUID> ids = reservationRepository.findOccupiedTableIdsByClubAndId(clubId, time);
        return (ids != null) ? ids : new ArrayList<>();
    }

    public boolean isTableOccupied(UUID tableId, LocalDateTime time) {
        return reservationRepository.existsByClubTable_IdAndDate(tableId, time);
    }

    public void createReservation(UUID tableId, LocalDateTime time, int people) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client user = clientRepository.findByUsername(username);
//                .orElseThrow(() -> new RuntimeException("User not found"));

        ClubTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        Reservation reservation = new Reservation();
        reservation.setClubTable(table);
        reservation.setClient(user);
        reservation.setDate(time);
        reservation.setCount(people);

        reservationRepository.save(reservation);
    }
}
