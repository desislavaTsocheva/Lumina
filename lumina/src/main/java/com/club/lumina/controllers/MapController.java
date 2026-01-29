package com.club.lumina.controllers;

import com.club.lumina.dto.MapMarkerDTO;
import com.club.lumina.repositories.ClubRepository;
import com.club.lumina.repositories.EventRepository;
import com.club.lumina.services.ClubService;
import com.club.lumina.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clubs")
public class MapController {
    private final ClubService clubService;
    private final EventService eventService;

    public MapController(ClubService clubService, EventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }

    @GetMapping("/club-map")
    public String showMapPage() {
        return "club_map";
    }
}
