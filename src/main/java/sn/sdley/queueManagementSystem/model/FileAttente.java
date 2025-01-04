package sn.sdley.queueManagementSystem.model;

import java.util.List;

// FileAttente.java
public class FileAttente extends Personne {
    private Service service;
    private List<Client> clients;

    public FileAttente(String prenom, String nom, Service service, List<Client> clients) {
//        super(prenom, nom);
        this.prenom = prenom;
        this.nom = nom;
        this.service = service;
        this.clients = clients;
    }

    // Getters, setters, constructeurs, méthodes
}