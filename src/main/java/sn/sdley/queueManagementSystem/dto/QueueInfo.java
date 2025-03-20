package sn.sdley.queueManagementSystem.dto;

public class QueueInfo {
    private String serviceNom;
    private String localisation;
    private int clientsEnAttente;
    private String numeroProchainTicket;
    private String numeroTicketEnCours; // ✅ Ajout du numéro du ticket en cours

    public QueueInfo(String serviceNom, String localisation, int clientsEnAttente,
                     String numeroProchainTicket, String numeroTicketEnCours) {
        this.serviceNom = serviceNom;
        this.localisation = localisation;
        this.clientsEnAttente = clientsEnAttente;
        this.numeroProchainTicket = numeroProchainTicket;
        this.numeroTicketEnCours = numeroTicketEnCours;
    }

    // Getters et Setters
    public String getServiceNom() { return serviceNom; }
    public String getLocalisation() { return localisation; }
    public int getClientsEnAttente() { return clientsEnAttente; }
    public String getNumeroProchainTicket() { return numeroProchainTicket; }
    public String getNumeroTicketEnCours() { return numeroTicketEnCours; } // ✅ Getter ajouté
}
