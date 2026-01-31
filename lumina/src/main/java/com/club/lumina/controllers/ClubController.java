package com.club.lumina.controllers;

import com.club.lumina.models.Club;
import com.club.lumina.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/allClubs")
    public String showClubs(@RequestParam(required = false) String town,
                            @RequestParam(required = false) String genre,
                            Model model) {
        model.addAttribute("clubs", clubService.searchClubs(town, genre));
        model.addAttribute("selectedTown", town);
        model.addAttribute("selectedGenre", genre);
        return "allClubs";
    }

    @GetMapping("/clubs/{id}")
    public String showClubDetails(@PathVariable String id, Model model) {
        try {
            Club club = clubService.findById(id);
            model.addAttribute("club", club);
            return "club-details";
        } catch (Exception e) {
            return "redirect:/allClubs";
        }
    }
}