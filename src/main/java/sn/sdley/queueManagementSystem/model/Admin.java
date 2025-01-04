package sn.sdley.queueManagementSystem.model;

import java.util.List;

public class Admin extends Personne {
    private List<FileAttente> listeFileAttente;

    public Admin(String prenom, String nom, List<FileAttente> listeFileAttente) {
//        super(prenom, nom);
        this.prenom = prenom;
        this.nom = nom;
        this.listeFileAttente = listeFileAttente;
    }
    // Getters, setters, constructeurs, méthodes
}
