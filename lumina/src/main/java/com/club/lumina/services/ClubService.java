package com.club.lumina.services;

import com.club.lumina.dto.MapMarkerDTO;
import com.club.lumina.models.Club;
import com.club.lumina.models.Event;
import com.club.lumina.repositories.ClubRepository;
import com.club.lumina.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final EventRepository eventRepository;

    public ClubService(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(UUID id) {
        Optional<Club> club = clubRepository.findById(id);
        return club.orElse(null);
    }

    public Club createClub(Club club) {
        return clubRepository.saveAndFlush(club);
    }

    public Club updateClub(Club club) {
        if(club.getId() == null) {
            throw new IllegalArgumentException("Club id is null");
        }
        return clubRepository.saveAndFlush(club);
    }

    public void deleteClub(UUID id) {
        clubRepository.deleteById(id);
    }

    public List<MapMarkerDTO> getClubsForMap(String genre, String artistName) {
        List<Event> activeEvents;
        if (artistName != null && !artistName.isEmpty()) {
            activeEvents = eventRepository.findAllByArtistNameIgnoreCase(artistName);
        } else if (genre != null && !genre.isEmpty()) {
            activeEvents = eventRepository.findAllByGenreIgnoreCase(genre);
        } else {
            activeEvents = eventRepository.findAllByEventDateBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        }

        return activeEvents.stream()
                .map(event -> {
                    Club club = event.getClub();
                    String status = calculateOccupancy(club.getCapacity(), club.getCount());

                    return new MapMarkerDTO(
                            club.getName(),
                            club.getLatitude(),
                            club.getLongitude(),
                            event.getPromotion(),
                            event.getArtist().getName(),
                            status
                    );
                })
                .collect(Collectors.toList());
    }

    private String calculateOccupancy(int capacity, int currentCount) {
        double occupancyPercent = ((double) currentCount / capacity) * 100;

        if (occupancyPercent >= 95) return "FULL";
        if (occupancyPercent >= 70) return "ALMOST_FULL";
        return "FREE";
    }
}
