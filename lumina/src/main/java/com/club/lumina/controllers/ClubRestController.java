package com.club.lumina.controllers;

import com.club.lumina.dto.MapMarkerDTO;
import com.club.lumina.services.ClubService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubRestController {
    private final ClubService clubService;

    public ClubRestController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping(value = "/map-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MapMarkerDTO> getMapData(
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String artist) {

        return clubService.getClubsForMap(style, artist);
    }
}