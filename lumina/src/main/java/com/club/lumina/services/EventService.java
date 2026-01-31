package com.club.lumina.services;

import com.club.lumina.models.Event;
import com.club.lumina.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(UUID id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    public Event updateEvent(Event event) {
        if(event.getId() == null) {
            throw new IllegalArgumentException("Event id is null");
        }
        return eventRepository.saveAndFlush(event);
    }

    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }
}
