package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Service {

    @Id
    @Column(unique = true, nullable = false)
    private String nom;
    private String description;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Localisation> localisations;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Service() {
    }

    public Service(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // Getters, setters, constructeurs, méthodes

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }
}
