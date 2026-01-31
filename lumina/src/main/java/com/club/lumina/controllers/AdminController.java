package com.club.lumina.controllers;

import com.club.lumina.dto.ClientRegisterDTO;
import com.club.lumina.dto.ReservationDTO;
import com.club.lumina.models.*;
import com.club.lumina.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ClientService clientService;
    private final ReservationService reservationService;
    private final ClubService clubService;
    private final ArtistService artistService;
    private final ClubTableService clubTableService;
    private final EventService eventService;

    public AdminController(ClientService clientService, ReservationService reservationService, ClubService clubService, ArtistService artistService, ClubTableService clubTableService, EventService eventService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.clubService = clubService;
        this.artistService = artistService;
        this.clubTableService = clubTableService;
        this.eventService = eventService;
    }

    @GetMapping("/clients")
    public String viewClients(Model model) {
        model.addAttribute("allClients", clientService.getClients());
        return "admin/clients";
    }

    @GetMapping("/reservations")
    public String viewAllReservations(Model model) {
        model.addAttribute("allReservations", reservationService.findAll());
        return "admin/reservations";
    }

    @GetMapping("/clubs")
    public String viewClubs(Model model) {
        model.addAttribute("allClubs", clubService.getClubs());
        return "admin/clubs";
    }

    @GetMapping("/events")
    public String viewEvents(Model model) {
        model.addAttribute("allEvents", eventService.getEvents());
        return "admin/events";
    }

    @PostMapping("/reservations/add")
    public String createReservation(@ModelAttribute ReservationDTO reservation) {
        reservationService.update(reservation);
        return "redirect:/admin/reservations";
    }

    @GetMapping("/reservations/add")
    public String showAddReservationForm(Model model) {
        List<Club> allClubs = clubService.findAllClubs();
        List<Artist> allArtists = artistService.getAllArtists();
        List<Client> allClients = clientService.getClients();
        List<ClubTable> allClubTables = clubTableService.getTables();

        model.addAttribute("clubs", allClubs);
        model.addAttribute("artists", allArtists);
        model.addAttribute("clients", allClients);
        model.addAttribute("tables", allClubTables);
        model.addAttribute("reservation", new ReservationDTO());

        return "admin/add_reservation";
    }

    @PostMapping("/clients/add")
    public String createClient(@ModelAttribute ClientRegisterDTO clientRegisterDTO) {
        clientService.addClient(clientRegisterDTO);
        return "redirect:/admin/clients";
    }


    @GetMapping("/clients/add")
    public String showAddClient(Model model) {
        model.addAttribute("client", new ClientRegisterDTO());
        return "admin/add_client";
    }

    @GetMapping("/clients/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Client client = clientService.getClient(id);
        ClientRegisterDTO dto = new ClientRegisterDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setEmail(client.getEmail());
        dto.setAge(client.getAge());
        dto.setPhoneNumber(client.getPhoneNumber());

        model.addAttribute("client", dto);
        return "admin/edit_client";
    }

    @PostMapping("/clients/edit")
    public String handleUpdate(@ModelAttribute("client") ClientRegisterDTO dto) {
        clientService.updateClient(dto);
        return "redirect:/admin/clients";
    }

    @PostMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return "redirect:/admin/clients";
    }
    @GetMapping("/reservations/edit/{id}")
    public String showEditReservationForm(@PathVariable UUID id, Model model) {
        Reservation reservation = reservationService.findById(id);

        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDate(reservation.getDate());
        dto.setCount(reservation.getCount());
        dto.setType(reservation.getType());
        dto.setDiscount(reservation.getDiscount());
        dto.setStatus(reservation.getStatus());
        dto.setClients_id(reservation.getClient().getId());
        dto.setClub_id(reservation.getClub().getId());
        dto.setTable_id(reservation.getClubTable().getId());

        model.addAttribute("clients", clientService.getClients());
        model.addAttribute("clubs", clubService.findAllClubs());
        model.addAttribute("tables", clubTableService.getTables());

        model.addAttribute("reservation", dto);
        return "admin/edit_reservation";
    }

    @PostMapping("/reservations/edit")
    public String updateReservation(@ModelAttribute("reservation") ReservationDTO dto) {
        reservationService.update(dto);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/reservations/delete/{id}")
    public String deleteReservation(@PathVariable UUID id) {
        reservationService.delete(id);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/clubs/delete/{id}")
    public String deleteClub(@PathVariable UUID id) {
        clubService.deleteClub(id);
        return "redirect:/admin/clubs";
    }

    @PostMapping("/clubs/add")
    public String createClub(@ModelAttribute Club club) {
        clubService.createClub(club);
        return "redirect:/admin/clubs";
    }


    @GetMapping("/clubs/add")
    public String showAddClub(Model model) {
        model.addAttribute("club", new Club());
        return "admin/add_club";
    }

    @GetMapping("/clubs/edit/{id}")
    public String showEditClubForm(@PathVariable UUID id, Model model) {
        Club club = clubService.getClubById(id);
        model.addAttribute("club", club);
        return "admin/edit_club";
    }

    @PostMapping("/clubs/edit")
    public String updateClub(@ModelAttribute("club") Club club) {
        clubService.updateClub(club);
        return "redirect:/admin/clubs";
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return "redirect:/admin/events";
    }

    @PostMapping("/events/add")
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/admin/events";
    }

    @GetMapping("/events/add")
    public String showAddEvent(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("clubs", clubService.findAllClubs());
        model.addAttribute("event", new Event());
        return "admin/add_event";
    }

    @GetMapping("/events/edit/{id}")
    public String showEditEventForm(@PathVariable UUID id, Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("clubs", clubService.findAllClubs());
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "admin/edit_event";
    }

    @PostMapping("/events/edit")
    public String updateEvent(@ModelAttribute("event") Event event) {
        eventService.updateEvent(event);
        return "redirect:/admin/events";
    }

}
