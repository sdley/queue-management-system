package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.dto.QueueInfo;
import sn.sdley.queueManagementSystem.model.Ticket;
import sn.sdley.queueManagementSystem.repositories.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Méthodes pour gérer les tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = getTicketById(id);
        if (ticket != null){
            ticket.setNumero(ticketDetails.getNumero());
            ticket.setPositionDansFile(ticketDetails.getPositionDansFile());
            ticket.setNombreDevant(ticketDetails.getNombreDevant());
            ticket.setStatus(ticketDetails.getStatus());
            ticket.setService(ticketDetails.getService());
            ticket.setClient(ticketDetails.getClient());
            ticket.setAgentId(ticketDetails.getAgentId());
            ticket.setIsPrevClient(ticketDetails.isPrevClient());
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    // Find clients `file attente` by service name
    public List<Ticket> getFileAttenteClientsByServiceName(String serviceName) {
        return ticketRepository.findClientsByServiceNom(serviceName);
    }

    // Find tickets by service name and localisation name
    public List<Ticket> getTicketsByServiceNameAndLocalisationName(String serviceName,
                                                                   String localisationName) {
        return ticketRepository.findByServiceNomAndLocalisation(serviceName,
                localisationName);
        /**
        * return ticketRepository.findByServiceAndLocalisation(serviceName,
                localisationName);
        */
    }

    // Methode pour gerer la generation de tickets avec la politique que nous avons definie
    public Integer getLastTicketNumberByService(String serviceName) {
        return ticketRepository.findLastTicketByService(serviceName)
                .map(ticket -> Integer.parseInt(ticket.getNumero().substring(3)))
                .orElse(100);
    }

    // Methode pour recuperer le ticket en court de traitement
    public Ticket getTicketByServiceAndLocationAndStatus(String serviceName,
                                                         String location,
                                                         String status) {
        return ticketRepository.findByServiceNomAndLocalisationAndStatus(
                serviceName, location, status);
    }

    public Ticket getTicketByServiceAndLocationAndStatusAndAgentId(
            String serviceName, String location, String status, Long agentId
    ) {
        // Récupère uniquement le premier ticket de la liste
        Pageable pageable = PageRequest.of(0, 1);

        /**
         * Au lieu de retourner directement une liste liste de Tickets comme ceci:
         * return ticketRepository.findByServiceNomAndLocalisationAndStatusAndAgentId(
         *                 serviceName, location, status, agentId, pageable
         *         );
         * Nous retournons un seul Ticket a la fois pour traitement!
         */
        Page<Ticket> tickets = ticketRepository
                .findByServiceNomAndLocalisationAndStatusAndAgentIdOrderByIdAsc(
                serviceName, location, status, agentId, pageable
        );

        return tickets.hasContent() ? tickets.getContent().get(0) : null;
    }

    public Ticket getTicketByServiceAndLocationAndStatusAndAgentIdReverseOrder(
            String serviceName, String location, String status, Long agentId
    ) {
        // Récupère uniquement le premier ticket de la liste
        Pageable pageable = PageRequest.of(0, 1);

        Page<Ticket> tickets = ticketRepository
                .findByServiceNomAndLocalisationAndStatusAndAgentIdOrderByIdDesc(
                        serviceName, location, status, agentId, pageable
                );

        return tickets.hasContent() ? tickets.getContent().get(0) : null;
    }

    // Methode pour recuperer le ticket du client precedent
    public Ticket getTicketByServiceAndLocationAndStatusAndAgentIdAndIsPrevClient(
            String serviceName, String location, String status, Long agentId,
            boolean prevClient ) {
        /**
         * Au lieu de retourner directement une liste liste de Tickets comme ceci:
         * return ticketRepository.findByServiceNomAndLocalisationAndStatusAndAgentIdAndPrevClient(
         *                 serviceName, location, status, agentId, prevClient
         *         );
         * Nous retournons un seul Ticket a la fois pour traitement!
         */
        // Récupère uniquement le premier ticket de la liste
        Pageable pageable = PageRequest.of(0, 1);
        Page<Ticket> tickets = ticketRepository
                .findByServiceNomAndLocalisationAndStatusAndAgentIdAndIsPrevClientOrderByIdDesc(
                        serviceName, location, status, agentId, prevClient, pageable
                );
        return tickets.hasContent() ? tickets.getContent().get(0) : null;
    }

    // Method for admin dashboard
    public List<QueueInfo> getQueuesOverview() {
        List<Object[]> results = ticketRepository.getQueueSummary();

        return results.stream().map(result -> {
            String serviceNom = (String) result[0];
            String localisation = (String) result[1];
            int clientsEnAttente = ((Number) result[2]).intValue();
            String numeroProchainTicket = (result[3] != null) ? (String) result[3] : "Aucun";

            // Récupération du ticket en cours de traitement
            String numeroTicketEnCours = ticketRepository
                    .getCurrentProcessingTicket(serviceNom, localisation);

            return new QueueInfo(serviceNom, localisation, clientsEnAttente,
                    numeroProchainTicket, numeroTicketEnCours);
        }).collect(Collectors.toList());
    }

}
