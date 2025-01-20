package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.queueManagementSystem.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
