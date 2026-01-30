package com.club.lumina.controllers;

import com.club.lumina.dto.ClientRegisterDTO;
import com.club.lumina.models.Client;
import com.club.lumina.services.ClientService;
import com.club.lumina.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final ReservationService reservationService;

    public ClientController(ClientService clientService, ReservationService reservationService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {
        String username = principal.getName();
        Client client = (Client) clientService.loadUserByUsername(username);
        model.addAttribute("client", client);
        return "client/profile";
    }

    @GetMapping("/reservations")
    public String viewMyReservations(Model model) {
        model.addAttribute("myReservations", reservationService.findAll());
        return "client/reservations";
    }

    @PostMapping("/profile/upload-photo")
    public String handlePhotoUpload(@RequestParam("profileImage") MultipartFile file, Principal principal) {
        try {
            String username = principal.getName();
            clientService.uploadProfilePicture(username, file);
        } catch (IOException e) {
            return "redirect:/client/profile?error=upload_failed";
        }
        return "redirect:/client/profile";
    }

}
