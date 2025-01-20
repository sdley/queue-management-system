package sn.sdley.queueManagementSystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.sdley.queueManagementSystem.model.Agent;
import sn.sdley.queueManagementSystem.service.AgentService;

@Controller
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public String listAgents(Model model) {
//        model.addAttribute("agents", agentService.getAllAgents());
        // Sérialiser l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String agentsJson = objectMapper.writeValueAsString(agentService.getAllAgents());
            model.addAttribute("agents", agentsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "agent"; // Retourne agent.jsp
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
}
