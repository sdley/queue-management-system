package sn.sdley.queueManagementSystem.model;

// Client.java
public class Client extends Personne {
    private String ticket;
    private int positionDansFile;
    private int nombreDeVant;
    private String adresse;

    public Client(String prenom, String nom, String ticket, int positionDansFile,
                  int nombreDeVant, String adresse) {
//        super(prenom, nom);
        this.prenom = prenom;
        this.nom = nom;
        this.ticket = ticket;
        this.positionDansFile = positionDansFile;
        this.nombreDeVant = nombreDeVant;
        this.adresse = adresse;
    }

    // Getters, setters, constructeurs, méthodes

}
