package com.club.lumina.dto;

import com.club.lumina.models.*;
import com.club.lumina.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClubRepository clubRepository;
    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;
    private final ClubTableRepository clubTableRepository; // Добави това
    private final ReservationRepository reservationRepository; // Добави това

    public DataLoader(ClubRepository clubRepository,
                      ArtistRepository artistRepository,
                      EventRepository eventRepository,
                      ClubTableRepository clubTableRepository,
                      ReservationRepository reservationRepository) {
        this.clubRepository = clubRepository;
        this.artistRepository = artistRepository;
        this.eventRepository = eventRepository;
        this.clubTableRepository = clubTableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (clubRepository.count() > 0) return;

        Artist galena = new Artist();
        galena.setName("Galena");
        galena.setGenre("Pop-folk");
        artistRepository.save(galena);

        Club eleven = new Club();
        eleven.setName("Eleven Club");
        eleven.setTown("Sofia");
        eleven.setAddress("Studentski grad");
        eleven.setLatitude(42.6518);
        eleven.setLongitude(23.3464);
        eleven.setCapacity(500);
        eleven.setSchedule("22:00 - 06:00");
        clubRepository.save(eleven);

        ClubTable vipTable = new ClubTable();
        vipTable.setTableNumber(1);
        vipTable.setCapacity(10);
        vipTable.setZone("VIP");
        vipTable.setClub(eleven);
        System.out.println("Saving tables...");
        clubTableRepository.save(vipTable);

        ClubTable regularTable = new ClubTable();
        regularTable.setTableNumber(15);
        regularTable.setCapacity(4);
        regularTable.setZone("Main Floor");
        regularTable.setClub(eleven);
        clubTableRepository.save(regularTable);

        Event event1 = new Event();
        event1.setArtist(galena);
        event1.setClub(eleven);
        event1.setEventDate(LocalDateTime.now().plusDays(2));
        event1.setPromotion("Ladies Night");
        event1.setGenre("Pop-folk");
        eventRepository.save(event1);

        Reservation reservation = new Reservation();
        reservation.setClub(eleven);
        reservation.setClubTable(vipTable);
        reservation.setDate(event1.getEventDate());
        reservation.setCount(8);
        reservation.setStatus("CONFIRMED");
        reservation.setType("VIP Booking");
        reservation.setDiscount(0);


        reservationRepository.save(reservation);

        System.out.println("--- Demo Data with Tables and Reservations ---");
    }
}