package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.*;
import sn.sdley.queueManagementSystem.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private LocalisationService localisationService;
    @Autowired
    private TicketService ticketService;

    // Get all agents
    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    // Create a new agent
    @PostMapping
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        Agent savedAgent = agentService.createAgent(agent);
        return ResponseEntity.ok(savedAgent);
    }

    // Get localisations by service name
    @GetMapping("/services/{nomService}/localisations")
    public ResponseEntity<List<Localisation>> getLocalisations(@PathVariable String nomService) {
        Service service = serviceService.getServiceByName(nomService);
        if (service == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(localisationService.getLocalisationsByService(service));
    }

    // Get agent dashboard info (client en cours + ticket)
    @GetMapping("/{agentId}/dashboard")
    public ResponseEntity<?> getAgentDashboard(@PathVariable Long agentId,
                                               @RequestParam String nomService,
                                               @RequestParam String nomLocalisation) {
        Agent agent = agentService.getAgentById(agentId);
        Service service = serviceService.getServiceByName(nomService);

        if (agent == null || service == null) {
            return ResponseEntity.badRequest().body("Agent ou service introuvable.");
        }

        Ticket currentTicket = ticketService.getTicketByServiceAndLocationAndStatusAndAgentId(
                nomService, nomLocalisation, "En Cours", agentId
        );

        Map<String, Object> response = new HashMap<>();
        response.put("agent", agent);
        response.put("service", service);
        response.put("localisation", nomLocalisation);
        response.put("currentTicket", currentTicket);
        response.put("currentClient", currentTicket != null ? currentTicket.getClient() : null);

        return ResponseEntity.ok(response);
    }

    // Appel client suivant
    @PostMapping("/{agentId}/next")
    public ResponseEntity<?> getNextClient(@PathVariable Long agentId,
                                           @RequestParam String nomService,
                                           @RequestParam String nomLocalisation) {

        Ticket current = ticketService.getTicketByServiceAndLocationAndStatusAndAgentId(
                nomService, nomLocalisation, "En Cours", agentId
        );
        Ticket previous = ticketService.getTicketByServiceAndLocationAndStatusAndAgentIdAndIsPrevClient(
                nomService, nomLocalisation, "Terminé", agentId, true
        );

        if (previous != null) {
            previous.setIsPrevClient(false);
            ticketService.updateTicket(previous.getId(), previous);
        }

        if (current != null) {
            current.setStatus("Terminé");
            current.setIsPrevClient(true);
            ticketService.updateTicket(current.getId(), current);
        }

        Ticket next = ticketService.getTicketByServiceAndLocationAndStatusAndAgentId(
                nomService, nomLocalisation, "En attente", null
        );

        if (next == null) {
            return ResponseEntity.ok(Map.of("message", "Aucun client en attente."));
        }

        next.setStatus("En Cours");
        next.setAgentId(agentId);
        ticketService.updateTicket(next.getId(), next);

        return ResponseEntity.ok(Map.of(
                "ticket", next,
                "client", next.getClient()
        ));
    }

    // Revenir au client précédent
    @PostMapping("/{agentId}/previous")
    public ResponseEntity<?> getPreviousClient(@PathVariable Long agentId,
                                               @RequestParam String nomService,
                                               @RequestParam String nomLocalisation) {

        Ticket current = ticketService.getTicketByServiceAndLocationAndStatusAndAgentId(
                nomService, nomLocalisation, "En Cours", agentId
        );
        if (current != null) {
            current.setStatus("En attente");
            current.setAgentId(null);
            ticketService.updateTicket(current.getId(), current);
        }

        Ticket prev = ticketService.getTicketByServiceAndLocationAndStatusAndAgentIdAndIsPrevClient(
                nomService, nomLocalisation, "Terminé", agentId, true
        );
        if (prev == null) {
            return ResponseEntity.ok(Map.of("message", "Aucun client précédent."));
        }

        prev.setStatus("En Cours");
        prev.setIsPrevClient(false);
        ticketService.updateTicket(prev.getId(), prev);

        Ticket beforePrev = ticketService.getTicketByServiceAndLocationAndStatusAndAgentIdReverseOrder(
                nomService, nomLocalisation, "Terminé", agentId
        );
        if (beforePrev != null) {
            beforePrev.setIsPrevClient(true);
            ticketService.updateTicket(beforePrev.getId(), beforePrev);
        }

        return ResponseEntity.ok(Map.of(
                "ticket", prev,
                "client", prev.getClient()
        ));
    }
}
