package sn.sdley.queueManagementSystem.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.model.Service;
import sn.sdley.queueManagementSystem.service.LocalisationService;
import sn.sdley.queueManagementSystem.service.ServiceService;

import java.util.*;

// ServiceController.java
@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private LocalisationService localisationService;

    @GetMapping
    public String listServices(Model model) {
        // Récupérer la liste des services et la transmettre à la vue
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        System.out.println("\nservices: " + services);

        // get localisations for each service
        Map<String, List<Localisation>> localisationsMap = new HashMap<>();
        for (Service service : services) {
            List<Localisation> localisationList = localisationService.getLocalisationsByService(service);
            localisationsMap.put(service.getNom(), localisationList);
        }
        model.addAttribute("localisations", localisationsMap);
        System.out.println("\nlocalisations: " + localisationsMap + "\n");


        return "services"; // Retourne services.jsp
    }


    // add service
    @GetMapping("/add")
    public String addService(Model model) {
        model.addAttribute("service", new Service());
        return "add-service"; // Retourne add-service.jsp: formulaire d'ajout de service
    }

//    @PostMapping("/add")
//    public String addService(Service service) {
//        // Enregistrer le service dans la base de données
//        serviceService.createService(service);
//        return "redirect:/services"; // Redirige vers la liste des services
//    }
    @PostMapping("/add")
    public String addService(
            @RequestParam String nom,
            @RequestParam String description,
            @RequestParam String localisations) {

        // Create a new Service object
        Service service = new Service();
        service.setNom(nom);
        service.setDescription(description);

        // Split the comma-separated string into a list
        List<String> localisationNames = Arrays.asList(localisations.split(","));

        // Create and associate localisations with the service
        List<Localisation> localisationList = new ArrayList<>();
        for (String localisationNom : localisationNames) {
            Localisation localisation = new Localisation();
            localisation.setNom(localisationNom);
            localisation.setService(service); // Set the service for the localisation
            localisationList.add(localisation);
        }

        // Set the localisations to the service
        service.setLocalisations(localisationList);

        // Save the service (this will also save the localisations due to CascadeType.ALL)
        serviceService.createService(service);

        return "redirect:/services"; // Redirect to the list of services
    }

    @GetMapping("/services/{id}")
    public String serviceDetails(@PathVariable int id, Model model) {
        // Récupérer les détails du service et les transmettre à la vue
        return "service-details";
    }


}
