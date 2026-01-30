package com.club.lumina.services;

import com.club.lumina.models.Artist;
import com.club.lumina.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(UUID id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.orElse(null);
    }

    public Artist updateArtist(Artist artist) {
        if(artist.getId() == null) {
            throw new IllegalArgumentException("Artist id is null");
        }
        return artistRepository.saveAndFlush(artist);
    }

    public void saveArtist(Artist artist) {
        artistRepository.saveAndFlush(artist);
    }

    public void deleteArtist(UUID id) {
        artistRepository.deleteById(id);
    }
}
