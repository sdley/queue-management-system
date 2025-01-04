package sn.sdley.queueManagementSystem.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sn.sdley.queueManagementSystem.model.Client;
import sn.sdley.queueManagementSystem.service.ClientService;

// ClientController.java
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/new")
    public String newClient(Model model) {
        // Préparer la vue pour la création d'un nouveau client
        return "client-form";
    }

    @PostMapping("/client/save")
    public String saveClient(@ModelAttribute Client client, Model model) {
        // Enregistrer le nouveau client
        return "redirect:/";
    }
}
