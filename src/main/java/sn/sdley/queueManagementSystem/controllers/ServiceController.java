package sn.sdley.queueManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.sdley.queueManagementSystem.model.Service;
import sn.sdley.queueManagementSystem.service.ServiceService;

import java.util.List;

// ServiceController.java
@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public String listServices(Model model) {
        // Récupérer la liste des services et la transmettre à la vue
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "services"; // Retourne services.jsp
    }

    @GetMapping("/add")
    public String addService(Model model) {
        model.addAttribute("service", new Service());
        return "add-service"; // Retourne add-service.jsp: formulaire d'ajout de service
    }

    @GetMapping("/service/{id}")
    public String serviceDetails(@PathVariable int id, Model model) {
        // Récupérer les détails du service et les transmettre à la vue
        return "service-details";
    }

    @PostMapping("/add")
    public String addService(Service service) {
        // Enregistrer le service dans la base de données
        serviceService.createService(service);
        return "redirect:/services"; // Redirige vers la liste des services
    }
}
