package com.club.lumina.controllers; // Провери това!

import org.springframework.web.bind.annotation.*;
import com.club.lumina.dto.MapMarkerDTO;
import com.club.lumina.services.ClubService;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubRestController {

    private final ClubService clubService;

    public ClubRestController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/search")
    public List<MapMarkerDTO> searchClubs(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String artist) {
        return clubService.getClubsForMap(genre, artist);
    }
}