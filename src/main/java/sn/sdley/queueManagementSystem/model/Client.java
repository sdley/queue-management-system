package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
    Nous pourrions egalement creer une classe Ticket
    et mettre la jointure entre Ticket et Client!

 */

@Entity
public class Client extends Personne {
    private Long ticketId;
    private String ticket; // nom du ticket
    private int positionDansFile;
    private int nombreDeVant;
    private String adresse;

    // Relation inverse entre Client et FileAttente
    @ManyToOne
    @JoinColumn(name = "fileAttente_id")
    private FileAttente fileAttente;

    public Client() {
    }

    public Client(Personne personne, String ticket, int positionDansFile,
                  int nombreDeVant, String adresse) {

        this.ticket = ticket;
        this.positionDansFile = positionDansFile;
        this.nombreDeVant = nombreDeVant;
        this.adresse = adresse;
    }

    // Getters, setters, constructeurs, méthodes


    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public FileAttente getFileAttente() {
        return fileAttente;
    }

    public void setFileAttente(FileAttente fileAttente) {
        this.fileAttente = fileAttente;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getPositionDansFile() {
        return positionDansFile;
    }

    public void setPositionDansFile(int positionDansFile) {
        this.positionDansFile = positionDansFile;
    }

    public int getNombreDeVant() {
        return nombreDeVant;
    }

    public void setNombreDeVant(int nombreDeVant) {
        this.nombreDeVant = nombreDeVant;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
