package sn.sdley.queueManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.service.LocalisationService;

@Controller
@RequestMapping("/localisation")
public class LocalisationController {

    @Autowired
    private LocalisationService localisationService;

    // Méthodes pour gérer les localisations
    @GetMapping
    public String listLocalisations(Model model) {
        model.addAttribute("localisations", localisationService.getAllLocalisations());
        return "localisation"; // Retourne localisation.jsp
    }

    @GetMapping("/add")
    public String addLocalisation(Model model) {
        model.addAttribute("localisation", new Localisation());
        return "localisation"; // Formulaire d'ajout
    }

    @PostMapping("/add")
    public String addLocalisation(@ModelAttribute Localisation localisation) {
        localisationService.createLocalisation(localisation);
        return "redirect:/localisation"; // Redirige vers la liste des localisations
    }
}
