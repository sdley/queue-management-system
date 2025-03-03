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
    public String genererTicket(@RequestParam String serviceId,
                                @RequestParam Long clientId,
                                @RequestParam String localisationId,
                                RedirectAttributes redirectAttributes) {
        // Récupérer le service
        Service service = serviceService.getServiceByName(serviceId);

        if (service == null) {
            redirectAttributes.addFlashAttribute("error",
                    "Service introuvable.");
            return "redirect:/error";
        }

        // Récupérer la file d'attente pour le service
        FileAttente fileAttente = fileAttenteService.getFileAttenteByServiceName(service.getNom());

        // Generer un nouveau ticket
        Ticket ticket = new Ticket();

        int positionDansFile;
        int nombreDevant;

        // Si aucune file d'attente n'existe, en créer une
        if (fileAttente == null) {
            fileAttente = new FileAttente();
            fileAttente.setService(service);
            fileAttente.setClients(new ArrayList<>()); // Initialiser la liste des clients
            fileAttenteService.createFileAttente(fileAttente);

            // Calculer la position dans la file et le nombre de personnes devant
            positionDansFile = 1;
            nombreDevant = 0;
        }else{
            // Calculer la position dans la file et le nombre de personnes devant
            positionDansFile = fileAttente.getClients().toArray().length + 1;
            nombreDevant = fileAttente.getClients().toArray().length;
            System.out.println("\nfileAttente.getClients() " +
                    fileAttente.getClients() + "\n\n");
            System.out.println("\nfileAttente.getClients().size() " +
                    fileAttente.getClients().size() + "\n\n");
        }

        // Completer les details du ticket
        ticket.setNumero(generateTicketNumber());
        ticket.setPositionDansFile(positionDansFile);
        ticket.setNombreDevant(nombreDevant);
        ticket.setService(service);
        ticket.setStatus("En attente");

        // Associer le client au ticket
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            redirectAttributes.addFlashAttribute("error",
                    "Client introuvable.");
            return "redirect:/error";
        }
        ticket.setClient(client);

        // Ajouter le client à la file d'attente
        fileAttente.getClients().add(client);
        fileAttenteService.updateFileAttente(fileAttente.getId(), fileAttente);

        // Enregistrer le ticket
        ticketService.createTicket(ticket);

        // Rediriger vers la page des détails du ticket
        redirectAttributes.addAttribute("ticketId", ticket.getId());
        return "redirect:/ticket/ticketDetails";
    }

    // Méthode pour générer un numéro de ticket unique
    private int generateTicketNumber() {
        // Logique pour générer un numéro unique, par exemple, en incrémentant le dernier numéro
        return (int) (Math.random() * 10000); // obtenir un nombre aléatoire entre 0 et 9999

    }





}
