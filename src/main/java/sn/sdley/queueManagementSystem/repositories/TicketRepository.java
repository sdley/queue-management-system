package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.queueManagementSystem.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
