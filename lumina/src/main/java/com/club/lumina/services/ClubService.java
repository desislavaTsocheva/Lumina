package com.club.lumina.services;

import com.club.lumina.models.Club;
import com.club.lumina.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ClubService {
    private final ClubRepository clubRepository;
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
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

}
