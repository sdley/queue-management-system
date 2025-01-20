package sn.sdley.queueManagementSystem.model;

//import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Personne {
//    Une classe d'entité doit suivre les conventions de nommage JavaBean et avoir des
//    champs privés avec des méthodes getter et setter publiques

//    Lorsque l'application est initialisée, JPA crée une table correspondante dans la
//    base de données avec le même nom que la classe d'entité. Cependant, si vous
//    souhaitez utiliser un nom différent pour la table dans la base de données, vous
//    pouvez utiliser l'annotation @Table dans votre classe d'entité et spécifier le nom
//    souhaité comme valeur pour l'annotation.

    /*
        @Id indique que le champ annoté est la clé primaire de l'entité.
        @GeneratedValue est utilisé pour spécifier la stratégie de génération de clé primaire.
        indique que la valeur de la clé primaire sera générée automatiquement par la base de données.
        strategy = GenerationType.IDENTITY spécifie que la base de données doit gérer
        l'incrémentation de la clé primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;

//    public Personne(String prenom, String nom) {
//        this.prenom = prenom;
//        this.nom = nom;
//    }

    public Personne() {
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
