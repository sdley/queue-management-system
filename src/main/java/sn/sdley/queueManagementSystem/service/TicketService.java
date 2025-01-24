package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.model.Ticket;
import sn.sdley.queueManagementSystem.repositories.TicketRepository;

import java.util.List;

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
            ticket.setNomService(ticketDetails.getNomService());
            ticket.setPositionDansFile(ticketDetails.getPositionDansFile());
            ticket.setNombreDeVant(ticketDetails.getNombreDeVant());
            ticket.setStatusTicket(ticketDetails.getStatusTicket());
            ticket.setClient(ticketDetails.getClient());
            return ticketRepository.save(ticket);
        }
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }




}
