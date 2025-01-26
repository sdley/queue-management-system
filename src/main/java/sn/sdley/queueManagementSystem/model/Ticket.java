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
    private String nomService; // nom du ticket
    private int numero; // numero du ticket
    private int positionDansFile;
    private int nombreDeVant;
    private String statusTicket;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id") // nom_service est le nom de la colonne dans la table ticket
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

    public Ticket(String nomService, int positionDansFile, int nombreDeVant, String statusTicket) {
        this.nomService = nomService;
        this.positionDansFile = positionDansFile;
        this.nombreDeVant = nombreDeVant;
        this.statusTicket = statusTicket;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
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

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

