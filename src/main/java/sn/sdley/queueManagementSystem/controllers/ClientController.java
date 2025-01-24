package sn.sdley.queueManagementSystem.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.Client;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.model.Service;
import sn.sdley.queueManagementSystem.model.Ticket;
import sn.sdley.queueManagementSystem.service.ClientService;
import sn.sdley.queueManagementSystem.service.LocalisationService;
import sn.sdley.queueManagementSystem.service.ServiceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ClientController.java
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private LocalisationService localisationService;

    @GetMapping
    public String listClients(Model model) {
        // Récupérer la liste des clients
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        // Sérialiser l'objet en JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String clientsJson = objectMapper.writeValueAsString(clients);
//            model.addAttribute("clients", clientsJson);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        // Récupérer la liste des services
        List<sn.sdley.queueManagementSystem.model.Service> services =
                serviceService.getAllServices();
        model.addAttribute("services", services);
        // serialize the services object to JSON
//        try {
//            String servicesJson = objectMapper.writeValueAsString(services);
//            model.addAttribute("servicesJson", servicesJson);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return "client"; // retourne client.jsp
    }

    // add client
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


    // choix service
    @PostMapping("/choixService")
    public String chooseService(@RequestParam String serviceId, Model model) {
        // Retrieve all services
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("services", services);

        // Find the selected service based on the serviceId
        Service selectedService = serviceService.getServiceByName(serviceId);

        // Fetch localisations based on the selected service
        List<Localisation> localisations = localisationService.getLocalisationsByService(selectedService);
        model.addAttribute("localisations", localisations);
        System.out.println("\nselectedService: " + selectedService + "\n");
        System.out.println("\nlocalisations: " + localisations + "\n");

        // Set selected service for display
        model.addAttribute("selectedService", serviceId);

        // Retrieve all clients
        model.addAttribute("clients", clientService.getAllClients());
        return "client"; // Return to the same JSP page
    }




}
