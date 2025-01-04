package sn.sdley.queueManagementSystem.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.sdley.queueManagementSystem.service.FileAttenteService;

// AdminController.java
@Controller
public class AdminController {
    @Autowired
    private FileAttenteService fileAttenteService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        // Récupérer les informations nécessaires pour le tableau de bord de l'admin
        return "admin-dashboard";
    }

    @GetMapping("/admin/file-attente/{id}")
    public String fileAttenteDetails(@PathVariable int id, Model model) {
        // Récupérer les détails de la file d'attente et les transmettre à la vue
        return "file-attente-details";
    }
}
