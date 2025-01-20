package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.*;

@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Localisation() {
    }

    // Getters, setters, constructeurs


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
