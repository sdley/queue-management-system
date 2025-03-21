package sn.sdley.queueManagementSystem.dto;

public class QueueInfo {
    private String serviceNom;
    private String localisation;
    private int clientsEnAttente;
    private String numeroTicketEnCours;
    private String numeroProchainTicket;

    public QueueInfo(String serviceNom, String localisation, int clientsEnAttente,
                     String numeroTicketEnCours, String numeroProchainTicket) {
        this.serviceNom = serviceNom;
        this.localisation = localisation;
        this.clientsEnAttente = clientsEnAttente;
        this.numeroTicketEnCours = formatTicketEnCours(numeroTicketEnCours, clientsEnAttente);
        this.numeroProchainTicket = formatProchainTicket(numeroProchainTicket, clientsEnAttente,
                                                         numeroTicketEnCours);
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

    public String getNumeroTicketEnCours() {
        return numeroTicketEnCours;
    }

    public String getNumeroProchainTicket() {
        return numeroProchainTicket;
    }


    // Méthode mise à jour pour formater Ticket en Cours
    private String formatTicketEnCours(String ticketEnCours, int clientsEnAttente) {
        if (ticketEnCours != null) {
            // Si un client est en cours de traitement, afficher son numéro
            return ticketEnCours;
        } else if (clientsEnAttente == 0) {
            // Aucun client en attente et aucun ticket en cours => File terminée
            return "<span style='color:green; font-weight:bold;'>File attente Terminée</span>";
        } else {
            // Aucun ticket en cours, clients en attente => Service Non Démarré
            return "<span style='color:red; font-weight:bold;'>Service Non Démarré</span>";
        }
    }


    // Méthode pour formater Prochain Ticket (avec Néant en vert)
    private String formatProchainTicket(String prochainTicket, int clientsEnAttente,
                                        String numeroTicketEnCours) {
        if (clientsEnAttente == 0 && numeroTicketEnCours != null) {
            return "<span style='color:green; font-weight:bold;'>Néant</span>";
        } else if (clientsEnAttente == 0 && numeroTicketEnCours == null) {
            return "<span style='color:green; font-weight:bold;'>File attente Terminée</span>";

        }
        return prochainTicket;
    }
}
