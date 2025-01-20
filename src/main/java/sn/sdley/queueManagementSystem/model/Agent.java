package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.Entity;

@Entity
public class Agent extends Personne{
    private String specialite;

    public Agent(Personne personne, String specialite) {
//        super(personne.getPrenom(), personne.getNom());
        this.specialite = specialite;
    }

    public Agent() {
    }

    // Getters and Setters

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
