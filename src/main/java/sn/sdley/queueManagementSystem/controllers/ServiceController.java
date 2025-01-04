package sn.sdley.queueManagementSystem.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.sdley.queueManagementSystem.service.ServiceService;

// ServiceController.java
@Controller
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public String services(Model model) {
        // Récupérer la liste des services et la transmettre à la vue
        return "services";
    }

    @GetMapping("/service/{id}")
    public String serviceDetails(@PathVariable int id, Model model) {
        // Récupérer les détails du service et les transmettre à la vue
        return "service-details";
    }
}
