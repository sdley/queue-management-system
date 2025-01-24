package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping
    public String listTickets(Model model) {
        // Récupérer la liste des tickets
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket";
    }

    // Generation de Ticket
    @PostMapping("/genererTicket")
    public String genererTicket(@RequestParam String serviceId,
                                @RequestParam Long clientId,
                                @RequestParam String localisation,
                                Model model) {
        // Récupérer le service
        Service service = serviceService.getServiceByName(serviceId); // Assurez-vous que cette méthode existe

        if (service == null) {
            model.addAttribute("error", "Service introuvable.");
            return "error"; // Rediriger vers une page d'erreur
        }

        // Récupérer la file d'attente pour le service
        FileAttente fileAttente = fileAttenteService.getFileAttenteByServiceName(service.getNom());

        // Si aucune file d'attente n'existe, en créer une
        if (fileAttente == null) {
            fileAttente = new FileAttente();
            fileAttente.setService(service);
            fileAttente.setClients(new ArrayList<>()); // Initialiser la liste des clients
            fileAttenteService.createFileAttente(fileAttente); // Méthode à créer dans FileAttenteService
        }

        // Calculer la position dans la file et le nombre de personnes devant
        int positionDansFile = fileAttente.getClients().size() + 1; // Position dynamique
        int nombreDeVant = fileAttente.getClients().size(); // Nombre de personnes devant

        // Créer un nouveau ticket
        Ticket ticket = new Ticket();
        ticket.setNomService(service.getNom());
        ticket.setNumero(generateTicketNumber()); // Générer un numéro de ticket unique
        ticket.setPositionDansFile(positionDansFile);
        ticket.setNombreDeVant(nombreDeVant);
        ticket.setStatusTicket("En attente");

        // Associer le client au ticket
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            model.addAttribute("error", "Client introuvable.");
            return "error"; // Rediriger vers une page d'erreur
        }
        ticket.setClient(client);

//        // Ajouter le ticket à la file d'attente
//        fileAttente.getClients().add(client); // Ajouter le client à la file d'attente
//        fileAttenteService.updateFileAttente(fileAttente); // Méthode à créer pour mettre à jour la file d'attente

        // Ajouter le ticket à la file d'attente
        fileAttente.getClients().add(client); // Ajouter le client à la file d'attente

        // Update the fileAttente with the new clients list
        fileAttenteService.updateFileAttente(fileAttente.getId(), fileAttente); // Pass the ID and updated object

        // Enregistrer le ticket
        ticketService.createTicket(ticket);

        // Ajouter le ticket à la vue pour confirmation ou redirection
        model.addAttribute("ticket", ticket);
        model.addAttribute("localisation", localisation); // Ajouter la localisation au modèle
        return "confirmation"; // Rediriger vers une page de confirmation
    }

    // Méthode pour générer un numéro de ticket unique
    private int generateTicketNumber() {
        // Logique pour générer un numéro unique, par exemple, en incrémentant le dernier numéro
        return (int) (Math.random() * 10000); // Remplacez par votre logique réelle
    }


    //@PostMapping("/genererTicket")
//public String genererTicket(@RequestParam String serviceId,
//                            @RequestParam String localisationId,
//                            @RequestParam Long clientId,
//                            Model model) {
//    // Récupérer le service et la localisation
//    Service service = serviceService.getServiceByName(serviceId);
//    Localisation localisation = localisationService.getLocalisationByName(localisationId);
//    Client client = clientService.getClientById(clientId); // Récupérer le client par ID
//
//    if (service == null || localisation == null || client == null) {
//        model.addAttribute("error", "Service, Localisation ou Client introuvable.");
//        return "error"; // Rediriger vers une page d'erreur si l'un des éléments est introuvable
//    }
//
//    // Logique pour générer un numéro de ticket
//    int numeroTicket = generateTicketNumber(); // Méthode à définir pour générer un numéro unique
//
//    // Créer un nouveau ticket
//    Ticket ticket = new Ticket();
//    ticket.setNomService(service.getNom());
//    ticket.setNumero(numeroTicket);
//    ticket.setPositionDansFile(1); // Par exemple, position initiale
//    ticket.setNombreDeVant(0); // Initialiser à 0 ou selon votre logique
//    ticket.setStatusTicket("En attente"); // Statut initial
//    ticket.setClient(client); // Associer le client au ticket
//
//    // Enregistrer le ticket
//    ticketService.createTicket(ticket);
//
//    // Ajouter le ticket à la vue pour confirmation ou redirection
//    model.addAttribute("ticket", ticket);
//    return "confirmation"; // Rediriger vers une page de confirmation
//}
//
//    // Méthode pour générer un numéro de ticket unique
//    private int generateTicketNumber() {
//        // Logique pour générer un numéro unique, par exemple, en incrémentant le dernier numéro
//        // Vous pouvez également utiliser un service pour obtenir le dernier numéro de ticket
//        return (int) (Math.random() * 10000); // Remplacez par votre logique réelle
//    }


}
