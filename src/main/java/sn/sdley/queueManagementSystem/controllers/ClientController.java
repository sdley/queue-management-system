package sn.sdley.queueManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.model.Client;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.model.Service;
import sn.sdley.queueManagementSystem.service.ClientService;
import sn.sdley.queueManagementSystem.service.LocalisationService;
import sn.sdley.queueManagementSystem.service.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private LocalisationService localisationService;

    // GET /api/clients : liste de tous les clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    // POST /api/clients : ajouter un client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client saved = clientService.createClient(client);
        return ResponseEntity.ok(saved);
    }

    // GET /api/services : liste des services
    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    // GET /api/clients/localisations?service=xxx : récupère les localisations d’un service donné
    @GetMapping("/localisations")
    public ResponseEntity<List<Localisation>> getLocalisations(@RequestParam String service) {
        Service s = serviceService.getServiceByName(service);
        if (s == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(localisationService.getLocalisationsByService(s));
    }
}
