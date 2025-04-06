package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.*;
import sn.sdley.queueManagementSystem.service.*;

import java.util.*;

@RestController
@RequestMapping("/api/tickets")
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

    private static final int START_NUMBER = 100;

    // ✅ Obtenir les détails d'un ticket par ID
    @GetMapping("/{ticketId}")
    public Map<String, Object> getTicketDetails(@PathVariable Long ticketId) {
        Map<String, Object> response = new HashMap<>();

        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null) {
            response.put("error", "Ticket introuvable.");
            return response;
        }

        Ticket ticketEnCours = ticketService.getTicketByServiceAndLocationAndStatus(
                ticket.getService().getNom(),
                ticket.getLocalisation(),
                "En cours"
        );

        response.put("ticket", ticket);
        response.put("numTicketEnCours", ticketEnCours != null ? ticketEnCours.getNumero() : null);
        response.put("nomService", ticket.getService() != null ? ticket.getService().getNom() : null);
        return response;
    }

    // ✅ Générer un nouveau ticket
    @PostMapping
    public Map<String, Object> genererTicket(@RequestBody Map<String, String> requestData) {
        Map<String, Object> response = new HashMap<>();

        String nomService = requestData.get("nomService");
        String nomLocalisation = requestData.get("nomLocalisation");
        Long clientId = Long.valueOf(requestData.get("clientId"));

        // Vérif
        Service service = serviceService.getServiceByName(nomService);
        if (service == null) {
            response.put("error", "Service introuvable.");
            return response;
        }

        if (nomLocalisation == null || nomLocalisation.trim().isEmpty()) {
            response.put("error", "Veuillez sélectionner une localisation.");
            return response;
        }

        // File d’attente
        FileAttente fileAttente = fileAttenteService
                .getFileAttenteByServiceName(service.getNom());
        if (fileAttente == null) {
            fileAttente = new FileAttente();
            fileAttente.setService(service);
            fileAttente.setClients(new ArrayList<>());
            fileAttente = fileAttenteService.createFileAttente(fileAttente);
        }

        int positionDansFile = ticketService
                .getTicketsByServiceNameAndLocalisationName(nomService, nomLocalisation).size() + 1;
        int nombreDevant = positionDansFile - 1;

        // Création du ticket
        Ticket ticket = new Ticket();
        ticket.setNumero(generateTicketNumber(nomService));
        ticket.setPositionDansFile(positionDansFile);
        ticket.setNombreDevant(nombreDevant);
        ticket.setService(service);
        ticket.setStatus("En attente");
        ticket.setLocalisation(nomLocalisation);

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            response.put("error", "Client introuvable.");
            return response;
        }

        ticket.setClient(client);
        fileAttente.getClients().add(client);
        fileAttenteService.updateFileAttente(fileAttente.getId(), fileAttente);
        ticketService.createTicket(ticket);

        response.put("message", "Ticket généré avec succès.");
        response.put("ticket", ticket);
        return response;
    }

    // ✅ Génération du numéro de ticket
    private String generateTicketNumber(String serviceName) {
        String prefix = serviceName.substring(0, Math.min(3, serviceName.length())).toUpperCase();
        Integer lastNumber = ticketService.getLastTicketNumberByService(serviceName);
        int newNumber = (lastNumber != null) ? lastNumber + 1 : START_NUMBER;
        return prefix + newNumber;
    }
}
