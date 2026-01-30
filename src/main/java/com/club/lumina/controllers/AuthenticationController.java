package com.club.lumina.controllers;

import com.club.lumina.dto.ClientRegisterDTO;
import com.club.lumina.models.Client;
import com.club.lumina.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping(value = "/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String register(@Valid @ModelAttribute("client") ClientRegisterDTO clientDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        boolean isUsernameAvailable = clientService.isUsernameAvailable(clientDTO.getUsername());
        boolean isEmailAvailable = clientService.isEmailAvailable(clientDTO.getEmail());

        if (!isUsernameAvailable) {
            bindingResult.rejectValue("username", "error.client", "Потребителското име е заето");
        }
        if (!isEmailAvailable) {
            bindingResult.rejectValue("email", "error.client", "Имейлът вече е регистриран");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        clientService.addClient(clientDTO);

        return "redirect:/login";
    }

}
