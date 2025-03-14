package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.sdley.queueManagementSystem.model.*;
import sn.sdley.queueManagementSystem.service.*;

import java.util.List;

@Controller
@RequestMapping("/agent")
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

    private int currentIndex = 0;

    @GetMapping
    public String agentsPage(Model model) {
        List<Agent> agents = agentService.getAllAgents();

        model.addAttribute("agents", agents);
        model.addAttribute("services", serviceService.getAllServices());

        return "agent";
    }

    @GetMapping("/add")
    public String addAgent(Model model) {
        model.addAttribute("agent", new Agent());
        return "add-agent"; // Retourne agent.jsp contient egalement le formulaire d'ajout
    }

    @PostMapping("/add")
    public String addAgent(@ModelAttribute Agent agent) {
        agentService.createAgent(agent);
        return "redirect:/agent"; // Redirige vers la liste des agents
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
        List<Localisation> localisations = localisationService
                .getLocalisationsByService(selectedService);
        model.addAttribute("localisations", localisations);

        // Set selected service for display
        model.addAttribute("selectedService", serviceId);

        // Retrieve all clients
        model.addAttribute("agents", agentService.getAllAgents());
        return "agent"; // Return to the same JSP page
    }

    // Agent details page - gestion clients
    @GetMapping("/agentDetails")
    public String getAgentDetails(
            @RequestParam("agentId") Long agentId,
            @RequestParam("nomService") String nomService,
            @RequestParam("nomLocalisation") String nomLocalisation,
            @RequestParam(value = "currentClient", required = false) Client currentClient,
            @RequestParam(value = "currentTicket", required = false) Ticket currentTicket,
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            Model model) {

        // Fetch data on DB
        Agent agent = agentService.getAgentById(agentId);
        Service service = serviceService.getServiceByName(nomService);

        model.addAttribute("agent", agent);
        model.addAttribute("service", service);
        model.addAttribute("localisation", nomLocalisation);

        if (currentClient != null) {
            model.addAttribute("currentClient", currentClient);
        }

        if (currentTicket != null) {
            model.addAttribute("currentTicket", currentTicket);
        }

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }


        // return agentDetails page
        return "agentDetails";
    }

    // Generation de Ticket
    @PostMapping("/traitementClient")
    public String genererTicket(@RequestParam String nomService,
                                @RequestParam Long agentId,
                                @RequestParam String nomLocalisation,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        // Retrieve the service
        Service service = serviceService.getServiceByName(nomService);
        if (service == null) {
            model.addAttribute("error", "Service introuvable.");
            return "error";
        }

        if (nomLocalisation == null || nomLocalisation.trim().isEmpty()) {
            model.addAttribute("error",
                    "Veuillez sélectionner une localisation");
            return "error";
        }

        if (agentId == null) {
            model.addAttribute("error",
                    "Veuillez sélectionner un agent");
            return "error";
        }

        // Redirect to agent details page
        redirectAttributes.addAttribute("nomService", service.getNom());
        redirectAttributes.addAttribute("nomLocalisation", nomLocalisation);
        redirectAttributes.addAttribute("agentId", agentId);
        return "redirect:/agent/agentDetails";
    }

    // Récupérer le client précédent
    @PostMapping("/previousClient")
    public String getPreviousClient(@RequestParam("serviceId") String nomService,
                                    @RequestParam String nomLocalisation,
                                    @RequestParam Long agentId,
                                    RedirectAttributes redirectAttributes) {
        Client currentClient;

        // Ticket en cours de Traitement
        Ticket currentTicket = ticketService
                .getTicketByServiceAndLocationAndStatusAndAgentId(
                        nomService, nomLocalisation, "En Cours", agentId
                );
        if (currentTicket != null) {
            // Le client doit attendre qu'on traite le precedent
            currentTicket.setStatus("En attente");
            currentTicket.setPrevClient(false);
            // Un autre agent doit etre en mesure de traiter ce client
            // Donc on le libere en retirant l'ID de l'agent
            currentTicket.setAgentId(null);
            ticketService.updateTicket(currentTicket.getId(), currentTicket);
        }

        // On recupere le client precedent
        Ticket prevTicket = ticketService
                .getTicketByServiceAndLocationAndStatusAndAgentIdAndPreClient(
                        nomService, nomLocalisation, "Terminé", agentId, true
                );
        if (prevTicket == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Il n'y a pas/plus de client precedent.");
            redirectAttributes.addAttribute("nomService", nomService);
            redirectAttributes.addAttribute("nomLocalisation", nomLocalisation);
            redirectAttributes.addAttribute("agentId", agentId);
            return "redirect:/agent/agentDetails";
        }

        // If prevTicket, we update it!
        prevTicket.setStatus("En Cours");
        prevTicket.setAgentId(agentId);
        ticketService.updateTicket(prevTicket.getId(), prevTicket);

        // On renvoie le client correspondant
        currentClient = prevTicket.getClient();

        redirectAttributes.addAttribute("currentTicket", prevTicket);
        redirectAttributes.addAttribute("currentClient", currentClient);
        redirectAttributes.addAttribute("nomService", nomService);
        redirectAttributes.addAttribute("nomLocalisation", nomLocalisation);
        redirectAttributes.addAttribute("agentId", agentId);

        return "redirect:/agent/agentDetails";
    }

    // Récupérer le client suivant
    @PostMapping("/nextClient")
    public String getNextClient(@RequestParam("serviceId") String nomService,
                                @RequestParam String nomLocalisation,
                                @RequestParam Long agentId,
                                RedirectAttributes redirectAttributes) {

        Client currentClient;

        // Ticket en cours de Traitement
        Ticket currentTicket = ticketService
                .getTicketByServiceAndLocationAndStatusAndAgentId(
                        nomService, nomLocalisation, "En Cours", agentId
                );

        if (currentTicket != null) {
            currentTicket.setStatus("Terminé");
            currentTicket.setPrevClient(true);
            ticketService.updateTicket(currentTicket.getId(), currentTicket);
        }

        // Client suivant / ticket suivant
        Ticket nextTicket = ticketService
                .getTicketByServiceAndLocationAndStatusAndAgentId(
                        nomService, nomLocalisation, "En attente", null
                );
        if (nextTicket == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Aucun client en attente.");
            redirectAttributes.addAttribute("nomService", nomService);
            redirectAttributes.addAttribute("nomLocalisation", nomLocalisation);
            redirectAttributes.addAttribute("agentId", agentId);
            return "redirect:/agent/agentDetails";
        }

        // If nextTicket, we update it!
        nextTicket.setStatus("En Cours");
        nextTicket.setAgentId(agentId);
        ticketService.updateTicket(nextTicket.getId(), nextTicket);

        // On renvoie le client correspondant
        currentClient = nextTicket.getClient();

        redirectAttributes.addAttribute("currentTicket", nextTicket);
        redirectAttributes.addAttribute("currentClient", currentClient);
        redirectAttributes.addAttribute("nomService", nomService);
        redirectAttributes.addAttribute("nomLocalisation", nomLocalisation);
        redirectAttributes.addAttribute("agentId", agentId);

        return "redirect:/agent/agentDetails";
    }
}
