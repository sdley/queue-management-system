package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.sdley.queueManagementSystem.model.*;
import sn.sdley.queueManagementSystem.service.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private LocalisationService localisationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FileAttenteService fileAttenteService;

    // Méthodes pour gérer les tickets
    @GetMapping("/ticketDetails")
    public String getTicketDetails(@RequestParam("ticketId") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);

        if (ticket == null) {
            model.addAttribute("error", "Ticket introuvable.");
            return "error";
        }

        model.addAttribute("ticket", ticket);
        return "ticketDetails";
    }

    // Generation de Ticket
    @PostMapping("/genererTicket")
    public String genererTicket(@RequestParam String nomService,
                                @RequestParam Long clientId,
                                @RequestParam String nomLocalisation,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        // Retrieve the service
        Service service = serviceService.getServiceByName(nomService);
        if (service == null) {
            model.addAttribute("error", "Service introuvable.");
            return "error";
        }

        System.out.println("\n\nLocalisation selectionnee : " + nomLocalisation);

        if (nomLocalisation == null || nomLocalisation.trim().isEmpty()) {
            model.addAttribute("error",
                    "Veuillez sélectionner une localisation");
            return "error";
        }

        // Retrieve or create the queue for the service
        FileAttente fileAttente = fileAttenteService
                .getFileAttenteByServiceName(service.getNom());
        if (fileAttente == null) {
            fileAttente = new FileAttente();
            fileAttente.setService(service);
            fileAttente.setClients(new ArrayList<>()); // Initialize the clients list
            fileAttente = fileAttenteService.createFileAttente(fileAttente); // Persist it
        }

        // Calculate position in the queue correctly
        /**
         * 1. Calculer la position dans la file d'attente
         * 2. Calculer le nombre de personnes devant
         * 3. Créer un nouveau ticket
         */

        // Nombre total de clients du service
        int nbreTotalServiceClients = ticketService
                .getFileAttenteClientsByServiceName(nomService).size();

        // Clients du Service pour une localisation donnée v1
//        int positionDansFile = ticketService
//                .getFileAttenteClientsByServiceName(nomService).size() + 1;
//        int nombreDevant = ticketService
//                .getFileAttenteClientsByServiceName(nomService).size();

        // Clients du Service pour une localisation donnée v2
        int positionDansFile = ticketService
                .getTicketsByServiceNameAndLocalisationName(nomService,
                        nomLocalisation).size() + 1;
        int nombreDevant = ticketService
                .getTicketsByServiceNameAndLocalisationName(nomService,
                        nomLocalisation).size();

        System.out.println("\n\nListe (nbre total) des Clients du service : " + nomService
                + " "  + ticketService.getFileAttenteClientsByServiceName(nomService));
        System.out.println("\n\nNombre de clients du service " + nomService + " : "
                + nbreTotalServiceClients);

        System.out.println("\n\nListe des Clients du service " + nomService + " a "
                + nomLocalisation + " : " + ticketService
                .getTicketsByServiceNameAndLocalisationName(nomService, nomLocalisation));
        System.out.println("\n\nPosition dans la file d'attente: " + positionDansFile);
        System.out.println("\n\nNombre de personnes devant: " + nombreDevant);

        // Create a new ticket
        Ticket ticket = new Ticket();
        ticket.setNumero(generateTicketNumber());
        ticket.setPositionDansFile(positionDansFile);
        ticket.setNombreDevant(nombreDevant);
        ticket.setService(service);
        ticket.setStatus("En attente");
        ticket.setLocalisation(nomLocalisation);

        // Retrieve the client
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            redirectAttributes.addFlashAttribute("error", "Client introuvable.");
            return "redirect:/error";
        }
        ticket.setClient(client);

        // Add the client to the queue and update the database
        fileAttente.getClients().add(client);
        fileAttenteService.updateFileAttente(fileAttente.getId(), fileAttente);

        // Save the ticket
        ticketService.createTicket(ticket);

        // Redirect to ticket details
        redirectAttributes.addAttribute("ticketId", ticket.getId());
        return "redirect:/ticket/ticketDetails";
    }


    // Méthode pour générer un numéro de ticket unique
    private int generateTicketNumber() {
        // Logique pour générer un numéro unique, par exemple, en incrémentant le dernier numéro
        return (int) (Math.random() * 10000); // obtenir un nombre aléatoire entre 0 et 9999

    }





}
