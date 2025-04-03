package sn.sdley.queueManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom; // nom de la localisation


    @ManyToOne
    @JoinColumn(name = "nom_service") // nom du service nom_service ou service_id
    @JsonBackReference
    private Service service;

    public Localisation() {
    }

    public Localisation(String nom, Service service) {
        this.nom = nom;
        this.service = service;
    }

    // Getters, setters, constructeurs


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
