package com.club.lumina.services;

import com.club.lumina.models.Club;
import com.club.lumina.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public String getCurrentEventArtist(Club club) {
        return eventRepository.findFirstByClubOrderByEventDateAsc(club)
                .map(event -> event.getArtist().getName())
                .orElse("Няма събития");
    }
}
