package com.club.lumina.services;

import com.club.lumina.models.ClubTable;
import com.club.lumina.repositories.ClubTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubTableService {
    private final ClubTableRepository clubTableRepository;

    public ClubTableService(ClubTableRepository clubTableRepository) {
        this.clubTableRepository = clubTableRepository;
    }

    public List<ClubTable> getTables() {
        return clubTableRepository.findAll();
    }
}
