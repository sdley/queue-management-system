package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.model.Agent;
import sn.sdley.queueManagementSystem.repositories.AgentRepository;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    // Méthodes pour gérer les agents
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id).orElse(null);
    }

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent updateAgent(Long id, Agent agentDetails) {
        Agent agent = getAgentById(id);
        if (agent != null){
            agent.setNom(agentDetails.getNom());
            agent.setPrenom(agentDetails.getPrenom());
            return agentRepository.save(agent);
        }
        return null;
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    public void deleteAllAgents() {
        agentRepository.deleteAll();
    }

//    public List<Agent> getAgentsByService(String service) {
//        return agentRepository.findByService(service);
//    }

}
