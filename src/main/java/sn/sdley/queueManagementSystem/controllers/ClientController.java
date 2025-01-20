package sn.sdley.queueManagementSystem.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.sdley.queueManagementSystem.model.Client;
import sn.sdley.queueManagementSystem.service.ClientService;

import java.util.List;

// ClientController.java
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        // Récupérer la liste des clients
        List<Client> clients = clientService.getAllClients();
//        model.addAttribute("clients", clients);
        // Sérialiser l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String clientsJson = objectMapper.writeValueAsString(clients);
            model.addAttribute("clients", clientsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "client"; // retourne client.jsp
    }

    @GetMapping("/add")
    public String newClient(Model model) {
        // Préparer la vue pour la création d'un nouveau client
        model.addAttribute("client", new Client());
        return "add-client"; // formulaire d'ajout
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client, Model model) {
        // Enregistrer le nouveau client
        clientService.createClient(client);
        return "redirect:/client"; // Redirige vers la liste des clients
    }

}
