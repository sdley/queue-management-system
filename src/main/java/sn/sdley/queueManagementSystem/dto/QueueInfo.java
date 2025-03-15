package sn.sdley.queueManagementSystem.dto;

public class QueueInfo {
    private String serviceNom;
    private String localisation;
    private int clientsEnAttente;
    private String numeroProchainTicket;

    public QueueInfo(String serviceNom, String localisation, int clientsEnAttente, String numeroProchainTicket) {
        this.serviceNom = serviceNom;
        this.localisation = localisation;
        this.clientsEnAttente = clientsEnAttente;
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
}
