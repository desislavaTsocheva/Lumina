package com.club.lumina.controllers;

import com.club.lumina.models.Client;
import com.club.lumina.services.ClientService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;

@Controller
@RequestMapping("/")
public class AuthenticationController {
    private final ClientService clientService;

    public AuthenticationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView register(@ModelAttribute Client client) {
        boolean isUsernameAvailable = clientService.isUsernameAvailable(client.getUsername());
        boolean isEmailAvailable = clientService.isEmailAvailable(client.getEmail());
        if(!isUsernameAvailable || !isEmailAvailable) {
            return new RedirectView("/register");
        }
        clientService.addClient(client);
        return new RedirectView("/");
    }

}
