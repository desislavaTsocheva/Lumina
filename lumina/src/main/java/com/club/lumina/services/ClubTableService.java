package com.club.lumina.services;

import com.club.lumina.models.Client;
import com.club.lumina.models.ClubTable;
import com.club.lumina.repositories.ClubTableRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubTableService {
    private final ClubTableRepository clubTableRepository;

    public ClubTableService(ClubTableRepository clubTableRepository) {
        this.clubTableRepository = clubTableRepository;
    }

    public List<ClubTable> getTables() {
        return clubTableRepository.findAll();
    }

    public List<ClubTable> findAllByClubId(UUID clubId) {
        Optional<ClubTable> table = clubTableRepository.findById(clubId);
        return Collections.singletonList(table.orElse(null));
    }
}
