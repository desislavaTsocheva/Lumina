package com.club.lumina.controllers;

import com.club.lumina.dto.ReservationDTO;
import com.club.lumina.models.Artist;
import com.club.lumina.models.Client;
import com.club.lumina.models.Club;
import com.club.lumina.repositories.ArtistRepository;
import com.club.lumina.repositories.ClientRepository;
import com.club.lumina.repositories.ClubRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ClubRepository clubRepository;
    private final ArtistRepository artistRepository;
    private final ClientRepository clientRepository;

    public ReservationController(ClubRepository clubRepository, ArtistRepository artistRepository, ClientRepository clientRepository) {
        this.clubRepository = clubRepository;
        this.artistRepository = artistRepository;
        this.clientRepository = clientRepository;
    }


    @GetMapping("/add")
    public String showAddReservationForm(Model model) {
        List<Club> allClubs = clubRepository.findAll();
        List<Artist> allArtists = artistRepository.findAll();
        List<Client> allClients = clientRepository.findAll();

        model.addAttribute("clubs", allClubs);
        model.addAttribute("artists", allArtists);
        model.addAttribute("clients", allClients);
        model.addAttribute("reservation", new ReservationDTO());

        return "admin/add_reservation";
    }
}