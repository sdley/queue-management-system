package sn.sdley.queueManagementSystem.dto;

public class QueueInfo {
    private String serviceNom;
    private String localisation;
    private int clientsEnAttente;
    private String numeroProchainTicket;
    private String ticketEnCours;  // Ajout du ticket en cours

    public QueueInfo(String serviceNom, String localisation, int clientsEnAttente,
                     String ticketEnCours, String numeroProchainTicket) {
        this.serviceNom = serviceNom;
        this.localisation = localisation;
        this.clientsEnAttente = clientsEnAttente;
        this.ticketEnCours = ticketEnCours;
        this.numeroProchainTicket = numeroProchainTicket;
    }

    public String getServiceNom() {
        return serviceNom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getClientsEnAttente() {
        return clientsEnAttente;
    }

    public String getNumeroProchainTicket() {
        return numeroProchainTicket;
    }

    public String getTicketEnCours() {
        return ticketEnCours;
    }
}
