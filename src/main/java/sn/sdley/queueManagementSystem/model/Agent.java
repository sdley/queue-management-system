package sn.sdley.queueManagementSystem.model;

public class Agent extends Personne{
    private String specialite;

    public Agent(Personne personne, String specialite) {
//        super(personne.getPrenom(), personne.getNom());
        this.specialite = specialite;
    }

    public Agent(String prenom, String nom, String specialite) {
//        super(prenom, nom);
        /*
        // There is no parameterless constructor available in
        // 'sn. sdley. queueManagementSystem. Personne
        this.prenom = prenom;
        this.nom = nom;
         */
        this.prenom = prenom;
        this.nom = nom;
        this.specialite = specialite;
    }


    // Getters and Setters


    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
