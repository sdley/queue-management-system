package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.model.Service;
import sn.sdley.queueManagementSystem.service.LocalisationService;
import sn.sdley.queueManagementSystem.service.ServiceService;

import java.util.*;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private LocalisationService localisationService;

    // Récupérer tous les services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    // Récupérer toutes les localisations d'un service donné
    @GetMapping("/localisations")
    public List<Localisation> getLocalisationsByService(@RequestParam String service) {
        Service s = serviceService.getServiceByName(service);
        return localisationService.getLocalisationsByService(s);
    }

    // Ajouter un nouveau service (avec ses localisations)
    @PostMapping
    public Service addService(@RequestBody Map<String, Object> requestData) {
        String nom = (String) requestData.get("nom");
        String description = (String) requestData.get("description");
        List<String> localisationNames = (List<String>) requestData.get("localisations");

        Service service = new Service();
        service.setNom(nom);
        service.setDescription(description);

        List<Localisation> localisationList = new ArrayList<>();
        for (String localisationNom : localisationNames) {
            Localisation localisation = new Localisation();
            localisation.setNom(localisationNom);
            localisation.setService(service);
            localisationList.add(localisation);
        }

        service.setLocalisations(localisationList);
        return serviceService.createService(service); // retourne le service enregistré
    }
}
