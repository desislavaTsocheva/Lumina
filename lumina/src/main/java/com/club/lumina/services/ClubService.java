package com.club.lumina.services;

import com.club.lumina.dto.MapMarkerDTO;
import com.club.lumina.models.Club;
import com.club.lumina.models.Event;
import com.club.lumina.repositories.ClubRepository;
import com.club.lumina.repositories.EventRepository;
import org.springframework.stereotype.Service;
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
        List<Event> activeEvents = new ArrayList<>();
        if (artistName != null && !artistName.isEmpty()) {
            activeEvents = eventRepository.findAllByArtistNameIgnoreCase(artistName);
        } else if (genre != null && !genre.isEmpty()) {
            activeEvents = eventRepository.findAllByGenreIgnoreCase(genre);
        }

        if (!activeEvents.isEmpty()) {
            return activeEvents.stream().map(this::convertToDTO).collect(Collectors.toList());
        }

        return clubRepository.findAll().stream()
                .map(club -> new MapMarkerDTO(
                        club.getName(),
                        club.getLatitude(),
                        club.getLongitude(),
                        "No special promotion",
                        "TBA",
                        calculateOccupancy(club.getCapacity(), club.getCount())
                ))
                .collect(Collectors.toList());
    }

    private MapMarkerDTO convertToDTO(Event event) {
        Club club = event.getClub();
        return new MapMarkerDTO(
                club.getName(),
                club.getLatitude(),
                club.getLongitude(),
                event.getPromotion(),
                event.getArtist().getName(),
                calculateOccupancy(club.getCapacity(), club.getCount())
        );
    }

    private String calculateOccupancy(int capacity, int currentCount) {
        double occupancyPercent = ((double) currentCount / capacity) * 100;

        if (occupancyPercent >= 95) return "FULL";
        if (occupancyPercent >= 70) return "ALMOST_FULL";
        return "FREE";
    }

    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }

    public List<Club> searchClubs(String town, String genre) {
        String townParam = (town != null && !town.isEmpty()) ? town : null;
        String genreParam = (genre != null && !genre.isEmpty()) ? genre : null;
        return clubRepository.findByFilters(townParam, genreParam);
    }

    public Club findById(String id) {
        return clubRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
    }
}
