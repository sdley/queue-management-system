package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Client extends Personne {
    private String adresse;

    // Relation inverse entre Client et FileAttente
    @ManyToOne
    @JoinColumn(name = "fileAttente_id")
    private FileAttente fileAttente;

    public Client() {
    }

    public Client(Personne personne, String adresse) {

        this.adresse = adresse;
    }

    // Getters, setters, constructeurs, méthodes

    public FileAttente getFileAttente() {
        return fileAttente;
    }

    public void setFileAttente(FileAttente fileAttente) {
        this.fileAttente = fileAttente;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
