package com.club.lumina.controllers;

import com.club.lumina.dto.ReservationDTO;
import com.club.lumina.services.ClientService;
import com.club.lumina.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ClientService clientService;
    private final ReservationService reservationService;

    public AdminController(ClientService clientService, ReservationService reservationService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
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

    @PostMapping("/reservations/add")
    public String createReservation(@ModelAttribute ReservationDTO reservationDto) {
        return "redirect:/success";
    }
}
