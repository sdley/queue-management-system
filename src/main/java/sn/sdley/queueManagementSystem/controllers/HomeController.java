package sn.sdley.queueManagementSystem.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sn.sdley.queueManagementSystem.service.FileAttenteService;

// HomeController.java
@Controller
public class HomeController {
    @Autowired
    private FileAttenteService fileAttenteService;

    @GetMapping("/")
    public String home(Model model) {
        // Récupérer les informations nécessaires pour la page d'accueil
        return "home";
    }
}