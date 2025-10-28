package com.club.lumina.services;

import com.club.lumina.models.Club;
import com.club.lumina.models.Reservation;
import com.club.lumina.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.saveAndFlush(reservation);
    }

    public Reservation updateClub(Reservation reservation) {
        if(reservation.getId() == null) {
            throw new IllegalArgumentException("Reservation id is null");
        }
        return reservationRepository.saveAndFlush(reservation);
    }

    public Reservation findById(UUID id) {
       Optional<Reservation> reservation= reservationRepository.findById(id);
        return reservation.orElse(null);
    }

    public void delete(UUID id) {
        reservationRepository.deleteById(id);
    }

}
