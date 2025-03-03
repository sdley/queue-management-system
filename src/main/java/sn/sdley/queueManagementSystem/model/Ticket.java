package sn.sdley.queueManagementSystem.model;


import jakarta.persistence.*;

@Entity
public class Ticket {

    /*
        En realite un client peut avoir plusieurs tickets!
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private String nomService; // nom du ticket
    private int numero; // numero du ticket
    private int positionDansFile;
    private int nombreDevant;
    private String status;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "nomService") // nom_service est le nom de la colonne dans la table ticket
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Ticket() {
    }

    public Ticket(int positionDansFile, int nombreDevant, String statusTicket) {
        this.positionDansFile = positionDansFile;
        this.nombreDevant = nombreDevant;
        this.status = statusTicket;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPositionDansFile() {
        return positionDansFile;
    }

    public void setPositionDansFile(int positionDansFile) {
        this.positionDansFile = positionDansFile;
    }

    public int getNombreDevant() {
        return nombreDevant;
    }

    public void setNombreDevant(int nombreDevant) {
        this.nombreDevant = nombreDevant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statusTicket) {
        this.status = statusTicket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

