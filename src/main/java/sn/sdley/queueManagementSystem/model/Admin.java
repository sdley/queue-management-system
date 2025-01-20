package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin extends Personne {
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileAttente> listeFileAttente;

    public Admin() {
    }
    public Admin(Personne personne, List<FileAttente> listeFileAttente) {

        this.listeFileAttente = listeFileAttente;
    }

    // Affichage lisible des objets Admin sinon on aura des adresses mémoires
//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + this.getId() +
//                ", nom='" + this.getNom() + '\'' +
//                ", prenom='" + this.getPrenom() + '\'' +
//                '}';
//    }
//    public String toString() {
//        return "Admin{" +
//                "id=" + this.getId() +
//                ", nom='" + this.getNom() + '\'' +
//                ", prenom='" + this.getPrenom() + '\'' +
//                '}';
//    }
    // Getters, setters, constructeurs, méthodes


    public List<FileAttente> getListeFileAttente() {
        return listeFileAttente;
    }

    public void setListeFileAttente(List<FileAttente> listeFileAttente) {
        this.listeFileAttente = listeFileAttente;
    }
}
