package com.club.lumina.controllers;

import com.club.lumina.models.Client;
import com.club.lumina.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {
        String username = principal.getName();
        Client client = (Client) clientService.loadUserByUsername(username);
        model.addAttribute("client", client);
        return "profile";
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
